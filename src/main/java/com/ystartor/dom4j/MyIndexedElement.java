package com.ystartor.dom4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;

public class MyIndexedElement extends DefaultElement {

    /** Lazily constructed index for elements */
    protected Map elementIndex;

    public MyIndexedElement(String name) {
        super(name);
    }

    public MyIndexedElement(QName qname) {
        super(qname);
    }

    public MyIndexedElement(QName qname, int attributeCount) {
        super(qname, attributeCount);
    }

    public Element element(String name) {
        return asElement(elementIndex().get(name));
    }

    public Element element(QName qName) {
        return asElement(elementIndex().get(qName));
    }

    public List elements(String name) {
        return asElementList(elementIndex().get(name));
    }

    public List elements(QName qName) {
        return asElementList(elementIndex().get(qName));
    }

    // Implementation methods
    // -------------------------------------------------------------------------
    protected Element asElement(Object object) {
        if (object instanceof Element) {
            return (Element) object;
        } else if (object != null) {
            List list = (List) object;

            if (list.size() >= 1) {
                return (Element) list.get(0);
            }
        }

        return null;
    }

    protected List asElementList(Object object) {
        if (object instanceof Element) {
            return createSingleResultList(object);
        } else if (object != null) {
            List list = (List) object;
            /*BackedList answer = createResultList();

            for (int i = 0, size = list.size(); i < size; i++) {
                answer.addLocal(list.get(i));
            }

            return answer;*/
            return list;
        }

        return createEmptyList();
    }

    /**
     * DOCUMENT ME!
     *
     * @param object
     *            DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @deprecated WILL BE REMOVED IN dom4j-1.6 !!
     */
    protected Iterator asElementIterator(Object object) {
        return asElementList(object).iterator();
    }

    // #### could we override the add(Element) remove(Element methods?
    protected void addNode(Node node) {
        super.addNode(node);

        if ((elementIndex != null) && node instanceof Element) {
            addToElementIndex((Element) node);
        }
    }

    protected void addNodeSuper(Node node) {
        super.addNode(node);
    }

    protected boolean removeNode(Node node) {
        if (super.removeNode(node)) {
            if ((elementIndex != null) && node instanceof Element) {
                removeFromElementIndex((Element) node);
            }

            return true;
        }

        return false;
    }

    protected Map elementIndex() {
        if (elementIndex == null) {
            elementIndex = createElementIndex();

            for (Iterator iter = elementIterator(); iter.hasNext();) {
                addToElementIndex((Element) iter.next());
            }
        }

        return elementIndex;
    }

    /**
     * A Factory Method to create the index for attributes
     *
     * @return DOCUMENT ME!
     */
    protected Map createAttributeIndex() {
        Map answer = createIndex();

        return answer;
    }

    /**
     * A Factory Method to create the index for elements
     *
     * @return DOCUMENT ME!
     */
    protected Map createElementIndex() {
        Map answer = createIndex();

        return answer;
    }

    /**
     * 添加元素到索引，若document树种该元素已存在现1-多次，则返回true，否则返回false
     * @param element
     * @return
     */
    protected boolean addToElementIndex(Element element) {
        QName qName = element.getQName();
        String name = qName.getName();
        addToElementIndex(qName, element);
        return addToElementIndex(name, element);
    }

    protected boolean addToElementIndex(Object key, Element value) {
        Object oldValue = elementIndex.get(key);

        if (oldValue == null) {
            elementIndex.put(key, value);
            return false;
        } else {
            if (oldValue instanceof List) {
                List list = (List) oldValue;
                list.add(value);
            } else {
                List list = createList();
                list.add(oldValue);
                list.add(value);
                elementIndex.put(key, list);
            }
            return true;
        }
    }

    protected void removeFromElementIndex(Element element) {
        QName qName = element.getQName();
        String name = qName.getName();
        removeFromElementIndex(qName, element);
        removeFromElementIndex(name, element);
    }

    protected void removeFromElementIndex(Object key, Element value) {
        Object oldValue = elementIndex.get(key);

        if (oldValue instanceof List) {
            List list = (List) oldValue;
            list.remove(value);
        } else {
            elementIndex.remove(key);
        }
    }

    /**
     * Factory method to return a new map implementation for indices
     *
     * @return DOCUMENT ME!
     */
    protected Map createIndex() {
        return new HashMap();
    }

    /**
     * Factory method to return a list implementation for indices
     *
     * @return DOCUMENT ME!
     */
    protected List createList() {
        return new ArrayList();
    }


}
