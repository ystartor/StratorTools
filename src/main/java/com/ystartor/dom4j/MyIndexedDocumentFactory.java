package com.ystartor.dom4j;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

public class MyIndexedDocumentFactory extends DocumentFactory {

    /** The Singleton instance */
    protected static transient MyIndexedDocumentFactory singleton = new MyIndexedDocumentFactory();

    /**
     * <p>
     * Access to the singleton instance of this factory.
     * </p>
     *
     * @return the default singleon instance
     */
    public static DocumentFactory getInstance() {
        return singleton;
    }

    // DocumentFactory methods
    // -------------------------------------------------------------------------
    public Element createElement(QName qname) {
        return new MyIndexedElement(qname);
    }

    public Element createElement(QName qname, int attributeCount) {
        return new MyIndexedElement(qname, attributeCount);
    }

}
