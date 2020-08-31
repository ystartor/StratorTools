package com.ystartor.dom4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUtil {


    public static final String STRING_NODE = "\\S+";// "((\\w:)?\\w)+";

    /**
     * 匹配规则，必须从XPath的根开始，若有属性则只能出现在最后一个 eg. /root/head/list[1]/@name
     */
    public static final String STRING_XPATH = "/\\S+(/\\S+(\\[\\d+\\])?)*(/@\\S+)?";

    public static final Pattern PATTERN_XPATH = Pattern.compile(STRING_XPATH);

    public static final Pattern PATTERN_XPATH_FUNCTION = Pattern.compile("(\\w+)\\(("
            + STRING_XPATH + ")\\)");

    public static Document createDocument(DocumentFactory factory, String encoding) {
        return factory.createDocument(encoding);
    }

    public static Document parseXml(DocumentFactory factory, Reader is) {
        try {
            SAXReader reader = new SAXReader(factory);
            return reader.read(is);
        } catch (Exception e) {
            throw new RuntimeException("解析XML得到Document失败", e);
        }
    }

    /**
     * 相比直接用DOM4J，可去xmlns=""，切设定换行符为\r\n，且按照组包反序生成xml
     *
     * @param node
     * @param encoding
     * @return
     */
    public static String node2Str(Node node, String encoding) {
        List<Node> nodes = Collections.singletonList(node);
        return nodes2Str(nodes, encoding);
    }

    public static String nodes2Str(List<Node> nodes, String encoding) {
        OutputFormat of = OutputFormat.createPrettyPrint();
        of.setEncoding(encoding);
        //of.setLineSeparator("\r\n");     // Modify by DK
        of.setLineSeparator("\n");
        of.setTrimText(false);
        of.setNewLineAfterDeclaration(false);
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw, of) {
            /* 是否需要定义namespace：处理对于使用xmlns="..."等命名空间会在子节点输出<node xmlns=""/>的问题 */
            @Override
            protected boolean isNamespaceDeclaration(Namespace ns) {
                if (ns == null || ("".equals(ns.getPrefix()) && "".equals(ns.getURI())))
                    return false;
                return super.isNamespaceDeclaration(ns);
            }
        };
        try {

            for (Node node : nodes) {
                xw.write(node);
            }
            xw.flush();
            return sw.toString();
        } catch (IOException e) {
            return "";
        }
    }

    private static void checkXPathValid(String xpath) {
        if (!PATTERN_XPATH.matcher(xpath).matches()) {
            throw new RuntimeException("XPath:" + xpath + " error");
        }
    }

    /**
     * 从Document中选择多个已有子节点（子元素、属性等），若为空返回emptyList
     */
    public static List<Node> selectNodes(Document doc, String xpath, Map<String, String> nsMaps) {
        Object ret = selectNode(doc, xpath, nsMaps);
        if (ret instanceof List)
            return (List<Node>) ret;
        List<Node> ns = new ArrayList<Node>(1);
        ns.add((Node) ret);
        return ns;
    }

    /**
     * 从Document中选择单个已有子节点（子元素、属性等），若为空返回null
     */
    public static Object selectSingleNode(Document doc, String xpath, Map<String, String> nsMaps) {
        Matcher m = PATTERN_XPATH_FUNCTION.matcher(xpath);
        String method = null;
        String path = xpath;
        if (m.find()) {
            method = m.group(1);
            path = m.group(2);
        }
        Object ret = selectNodeByXpath(doc, path, nsMaps);
        if (method != null)
            return xpathMethod(ret, method);

        if (ret instanceof List) {
            List<Node> list = (List<Node>) ret;
            if (list.size() == 0)
                return null;
            return list.get(0);
        }
        return ret;
    }

    private static Object xpathMethod(Object node, String method) {
        // FIXME 暂时只支持count函数
        if ("count".equals(method)) { // 统计节点个数
            if (node instanceof List) {
                List<Node> list = (List<Node>) node;
                return list.size();
            }
            if (node == null)
                return 0;
            return 1;
        }
        throw new RuntimeException("xpath function[" + method + "] is not supported!");
    }

    public static Object selectNodeByXpath(Document doc, String xpath, Map<String, String> nsMaps) {
        checkXPathValid(xpath);
        return doc.selectNodes(xpath);
    }

    /**
     * 从doc中根据xpath选择节点信息.采用解释方式代替基于jaxen的XPath引擎，减少内存消耗
     *
     * @param doc
     * @param xpath
     * @param nsMaps
     * @return Node或List<Node>
     */
    public static Object selectNode(Document doc, String xpath, Map<String, String> nsMaps) {
        checkXPathValid(xpath);

        // 准备NameSpace
        Map<String, Namespace> ns = new HashMap<String, Namespace>();
        if (nsMaps != null)
            for (Map.Entry<String, String> en : nsMaps.entrySet()) {
                ns.put(en.getKey(), new Namespace(en.getKey(), en.getValue()));
            }
        if (!ns.containsKey(""))
            ns.put("", new Namespace("", ""));

        StringTokenizer nodes = new StringTokenizer(xpath, "/");
        Element parent = doc.getRootElement();
        if (parent == null)
            return null;
        // 设置和检查根元素
        if (nodes.hasMoreTokens()) {
            String[] pn = getPrefixAndNameFromNodename(nodes.nextToken());
            if (!parent.getName().equals(pn[1]) && parent.getNamespace().equals(ns.get(pn[0])))
                return null;
        }
        while (nodes.hasMoreTokens()) {
            String node = nodes.nextToken();
            // 对于当前节点，则忽略处理
            if (".".equals(node))
                continue;
            if (node.startsWith("@")) {
                // 属性，其后面不可能再有子元素
                node = node.substring(1);
                String[] pn = getPrefixAndNameFromNodename(node);
                return parent.attribute(new QName(pn[1], ns.get(pn[0])));
            }
            String[] pn = getPrefixAndNameFromNodename(node);
            if (pn[1].contains("[")) {
                // 下标
                int idx = pn[1].indexOf("[");
                int lastIdx = pn[1].lastIndexOf("]");
                String xb = pn[1].substring(idx + 1, lastIdx);
                pn[1] = pn[1].substring(0, idx);
                int ixb = Integer.parseInt(xb) + 1;
                List<Element> list = parent.elements(new QName(pn[1], ns.get(pn[0])));
                int listSize = list.size();
                if (listSize >= ixb) {
                    // 在列表中已存在该下标元素，则使用该元素处理下一级
                    parent = list.get(ixb - 1);
                    continue;
                }
                return null;
            } else {
                if (nodes.hasMoreTokens()) {
                    Element e = parent.element(new QName(pn[1], ns.get(pn[0])));
                    if (e != null) {
                        // 该元素已存在，则使用该元素处理下一级
                        parent = e;
                        continue;
                    }
                    return null;
                } else {
                    return parent.elements(new QName(pn[1], ns.get(pn[0])));
                }
            }
        }
        return parent;
    }

    /** 根据节点全名获取其前缀与节点名 */
    private static String[] getPrefixAndNameFromNodename(String nodename) {
        String[] ret = new String[2];
        int ix = nodename.indexOf(":");
        if (ix > -1) {
            ret[0] = nodename.substring(0, ix);
            ret[1] = nodename.substring(ix + 1);
        } else {
            ret[0] = "";
            ret[1] = nodename;
        }
        return ret;
    }

    /**
     * 获取节点值，若节点内包含子节点，则返回子节点内容
     */
    public static String getNodeStringValue(Node node) {
        /*
         * if (node instanceof Element) { // 若node本身下有子节点，则节点值为node的子节点字符串 if
         * (((Element)node).elements().size() > 0) { List<Node> ns = ((List<Node>)((Element)
         * node).elements()); return XmlHelper.nodes2Str(ns, "GBK"); } }
         */
        return node.getStringValue();
    }

    /*
     * public static Node createXPathNode(DocumentFactory factory, Document doc, String xpath) {
     * checkXPathValid(xpath); List nodes = doc.selectNodes(xpath); }
     */
    /**
     * 在Document中创建子节点（子元素、属性等），若存在则返回，不存在则创建
     */
    public static Node createNode(DocumentFactory factory, Document doc, String xpath) {
        checkXPathValid(xpath);

        StringTokenizer nodes = new StringTokenizer(xpath, "/");
        Element parent = doc.getRootElement();
        // 设置和检查根元素
        if (nodes.hasMoreTokens()) {
            String node = nodes.nextToken();
            if (parent == null) {
                parent = factory.createElement(node);
                doc.setRootElement(parent);
            } else if (!parent.getName().equals(node))
                return null;
        }
        while (nodes.hasMoreTokens()) {
            String node = nodes.nextToken();
            // 对于当前节点，则忽略处理
            if (".".equals(node))
                continue;
            if (node.startsWith("@")) {
                // 属性，其后面不可能再有子元素
                node = node.substring(1);
                Attribute attr = parent.attribute(node);
                if (attr == null) {
                    // 属性不存在
                    attr = factory.createAttribute(parent, node, "");
                    parent.add(attr);
                }
                return attr;
            } else if (node.contains("[")) {
                // 下标
                int idx = node.indexOf("[");
                int lastIdx = node.lastIndexOf("]");
                String xb = node.substring(idx + 1, lastIdx);
                node = node.substring(0, idx);
                int ixb = Integer.parseInt(xb) + 1;
                List<Element> list = parent.elements(node); // 若Dom4j原生模式在大数据量处理时内存消耗较大
                int listSize = list.size();
                if (listSize >= ixb) {
                    // 在列表中已存在该下标元素，则使用该元素处理下一级
                    parent = list.get(ixb - 1);
                    continue;
                }
                Element e = null;
                // 创建不足的多个元素
                for (int i = listSize; i < ixb; i++) {
                    e = factory.createElement(node);
                    parent.add(e);
                }
                parent = e;
            } else {
                Element e = parent.element(node);
                if (e != null) {
                    // 该元素已存在，则使用该元素处理下一级
                    parent = e;
                    continue;
                }
                e = factory.createElement(node);
                parent.add(e);
                parent = e;
            }
        }

        return parent;
    }

    /**
     * 为最后一个元素节点增加下标
     *
     * @param xpath
     * @param idx
     * @return
     */
    public static String xpathLastNodeAddIdx(String xpath, int idx) {
        // checkXPathValid(xpath);
        StringTokenizer st = new StringTokenizer(xpath, "/");
        StringBuilder ret = new StringBuilder();
        if (xpath.startsWith("/"))
            ret.append("/");
        int i = 0;
        String token = "";
        int count = st.countTokens();
        for (; i < count; i++) {
            token = (String) st.nextElement();
            if (i == 0) {
                ret.append(token);
                if (i == count - 1 && !token.startsWith("@"))
                    ret.append("[" + idx + "]");
            } else if (i == count - 1) {
                if (token.startsWith("@"))
                    ret.append("[" + idx + "]/").append(token);
                else
                    ret.append("/").append(token).append("[" + idx + "]");
            } else {
                ret.append("/").append(token);
            }
        }
        return ret.toString();
    }

    /**
     * 若节点无子节点、无属性、且为空则删除，递归处理父元素节点
     *
     * @param e
     */
    public static void detachElementIfEmpty(Element e) {
        if (e == null)
            return;
        // 空元素:无子节点,无属性,且文本内容为空
        if (e.isTextOnly() && "".equals(e.getText()) && e.attributeCount() == 0) {
            Element parent = e.getParent();
            if (parent != null) {
                parent.remove(e);
                detachElementIfEmpty(parent);
            }
        }
    }

    /**
     * xpath增加命名空间前缀，只处理节点元素，不处理属性 1.如果xpath以/开头，则不对根节点加前缀 2.如果xpath不以/开头，则对根节点加前缀
     *
     * @param xpath
     * @param prefix
     * @return
     */
    public static String xpathAddNamespacePrefix(String xpath, String prefix) {
        // checkXPathValid(xpath);
        StringTokenizer st = new StringTokenizer(xpath, "/");
        StringBuilder ret = new StringBuilder();
        int i = 0;
        String token = "";
        int count = st.countTokens();
        for (; i < count; i++) {
            token = (String) st.nextElement();
            if (i == 0) {
                if (xpath.startsWith("/"))
                    ret.append("/").append(token);
                else {
                    if (token.startsWith("@"))
                        ret.append(token);
                    else
                        ret.append(prefix).append(":").append(token);
                }
                continue;
            }
            if (token.startsWith("@"))
                ret.append("/").append(token);
            else
                ret.append("/").append(prefix).append(":").append(token);
        }
        return ret.toString();
    }

    public static String validateXMLByXSD(DocumentFactory factory, InputStream xsdStream,
                                          InputStream xmlStream, String encoding) throws Exception {
        Document xmlDocument = XmlUtil.parseXml(factory,
                new InputStreamReader(xmlStream, encoding));
        InputSource xsdSource = new InputSource(xsdStream);

        Element errorElement = validateXMLByXSD(xsdSource, xmlDocument);
        String errorStr = "";
        if (errorElement.hasContent()) {
            errorStr = errorElement.asXML();
        }

        return errorStr;
    }

    public static String validateXMLByXSD(InputStream xsdStream, Document xmlDocument)
            throws Exception {

        InputSource xsdSource = new InputSource(xsdStream);

        Element errorElement = validateXMLByXSD(xsdSource, xmlDocument);
        String errorStr = "";
        if (errorElement.hasContent()) {
            errorStr = errorElement.asXML();
        }

        return errorStr;
    }

    public static Element validateXMLByXSD(final InputSource xsdSource, Document xmlDocument)
            throws Exception {
        // 创建默认的XML错误处理器
        XMLErrorHandler errorHandler = new XMLErrorHandler();
        //
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 在解析时验证XML内容
        factory.setValidating(true);
        // 指定有次代码生成的 解析器将提供对XML名称空间的支持
        factory.setNamespaceAware(true);
        SAXParser parser = factory.newSAXParser();

        // 设置XMLReader 的基础实现中的特定属性。核心功能和属性列表
        parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");
        parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "file:"
                + "temp.xsd");
        // 这里可以手动修改原的
        parser.getXMLReader().setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
                    IOException {
                return xsdSource;
            }
        });
        // 创建一个SAXValidator 校验工具，并设置校验工具的属性
        SAXValidator validator = new SAXValidator(parser.getXMLReader());

        // 设置校验工具的错误处理器，当发生错误时，可以从处理器对象中的到错误信息。
        validator.setErrorHandler(errorHandler);

        // 开始校验
        validator.validate(xmlDocument);

        return errorHandler.getErrors();

    }


}
