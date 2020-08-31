package com.ystartor.dom4j;

import com.message.v1.DecodeContext;
import com.message.v1.DecodeStrategy;
import com.message.v1.exception.DecodeException;
import com.message.v1.utils.DataObject;
import com.message.v1.utils.MessageUtils;
import com.ystartor.dom4j.util.XmlUtil;
import org.dom4j.*;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 2020年8月31日 10:05:32 公司ilink解析XML出现问题，重新搞搞看
 */
public class TestParseDecode {

    Document document = null;



    /** 前缀-命名空间键值对 */
    protected Map<String, String> nsMap;




    public TestParseDecode(){
    }

    @Test
    public void test() {
        //
        File file = new File("Y:\\leshan\\test.xml");
        byte[] xmlBs = file2Byte(file);
        /** 前缀-命名空间键值对 */
        Map<String, String> nsMap;
        Pattern pattern_namespaces = Pattern
                .compile("\\w+=.+(,\\w+=.+)*");
        try {
            document = XmlUtil.parseXml(getDocumentFactory(), new InputStreamReader(new ByteArrayInputStream(xmlBs),
                    "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            parseElement(document.getRootElement(), buffer);
            System.out.println(buffer.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void parseElement(Element element, StringBuffer buffer) {
        buffer.append("<"+element.getName());
        List<Attribute> attrs = element.attributes();
        if (attrs != null) {
            for (Attribute attr : attrs) {
                buffer.append(" "+attr.getName()+"=\""+attr.getValue()+"\"");
            }
        }
        buffer.append(">");

        Iterator<Node> iterator = element.nodeIterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof Element) {
                Element eleNode = (Element) node;
                parseElement(eleNode, buffer);
            }
            if (node instanceof Text) {
                Text text = (Text) node;
                buffer.append(text.getText());
            }
            if (node instanceof CDATA) {
                CDATA dataNode = (CDATA) node;
                buffer.append(dataNode.getText());
            }
            if (node instanceof Comment) {
                Comment comNode = (Comment) node;
                buffer.append(comNode.getText());
            }
        }
        buffer.append("</"+element.getName()+">");
    }



    public static byte[] file2Byte(File file){
        byte[] data = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1){
                baos.write(buffer, 0, len);
            }
            data = baos.toByteArray();
            fis.close();
            baos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    protected static DocumentFactory getDocumentFactory() {
        return MyIndexedDocumentFactory.getInstance();
    }

    public byte[] decodeField(Map<String, String> attrs, DecodeContext ctx) throws DecodeException {
        String prefix = ctx.getPrefixStack().peek();
        DataObject out = ctx.getDataBus();
        String xpathAttr = attrs.get("xpath");
        String name = attrs.get("name");
        try {
            xpathAttr = MessageUtils.replaceVariableInString(xpathAttr, prefix,
                    out);
            Object node = XmlUtil.selectSingleNode(document, xpathAttr, nsMap);
            if (node instanceof Element) {
                Element ele = (Element) node;
                List<Attribute> attributes = ele.attributes();
                if (attributes != null && !attributes.isEmpty()) {
                    for (Attribute attribute : attributes) {
                        out.set(name + "_" + attribute.getName(),
                                attribute.getValue());
                    }
                }
                //logger.debug("解包Field Path=" + xpathAttr + " val=" + ele.getStringValue());  //Modify by DK
                //logger.info("解包Field Path=" + xpathAttr + " val=" + ele.getStringValue());
                if(name.length()<6||!"safe_".equals(name.substring(0,5))){
                    //logger.info("Path=" + xpathAttr + " val=" + ele.getStringValue());
                }
                else{
                    //logger.info("Path=" + xpathAttr + " val=******");
                }
                return ele.getStringValue().getBytes("UTF-8");
            }
            if (node == null)
                return new byte[0];
            if (node instanceof Node)
                return ((Node) node).getStringValue().getBytes("UTF-8");
            if (node instanceof Integer)
                return ((Integer) node).toString().getBytes("UTF-8");
            return node.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}

