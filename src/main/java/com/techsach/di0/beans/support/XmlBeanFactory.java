package com.techsach.di0.beans.support;

import com.techsach.di0.beans.BeanDef;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlBeanFactory extends AbstractBeanFactory {

    private static final String ATTR_BEAN_ID = "id";
    private static final String ATTR_BEAN_CLASS_NAME = "className";

    public XmlBeanFactory(String xmlFilePath) {
        loadBeansFromXmlFile(xmlFilePath);
    }

    private void loadBeansFromXmlFile(String xmlFilePath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error while loading beans from file:[" + xmlFilePath + "]"
                    , e);
        }
        parseBeansFromXml(builder, xmlFilePath);
    }

    private void parseBeansFromXml(DocumentBuilder builder, String xmlFilePath) {
        Document document;
        try {
            document = builder.parse(xmlFilePath);
        } catch (SAXException e) {
            throw new RuntimeException("Error while parsing xml file:[" + xmlFilePath + "] for " +
                    "beans.", e);
        } catch (IOException e) {
            throw new RuntimeException("Unable to access xml file at:[" + xmlFilePath +"]", e);
        }
        NodeList beanNodeList = document.getElementsByTagName("bean");
        parseBeansFromNodeList(beanNodeList);
    }

    private void parseBeansFromNodeList(NodeList beanNodeList) {
        for(int i=0; i < beanNodeList.getLength(); i++) {
            Node node = beanNodeList.item(i);
            NamedNodeMap beanAttributes = node.getAttributes();
            Node beanIdNode = beanAttributes.getNamedItem(ATTR_BEAN_ID);
            Node beanClassNameNode = beanAttributes.getNamedItem(ATTR_BEAN_CLASS_NAME);
            if (beanIdNode == null || (beanClassNameNode == null
                    || beanClassNameNode.getNodeValue() == null)) {
                throw new RuntimeException("Invalid beanDef definition for beanDef:" + node);
            }

            BeanDef beanDef = createBeanDefFromAttributes(beanIdNode, beanClassNameNode);
            buildBeanFromBeanDef(beanDef);
        }
    }

    private BeanDef createBeanDefFromAttributes(Node beanIdNode, Node beanClassNameNode) {
        String beanClassName = beanClassNameNode.getNodeValue();
        String beanId = beanIdNode.getNodeValue();
        Class<?> beanClass;
        try {
            beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load class:[" + beanClassName +"], please " +
                    "check classpath is set correctly.", e);
        }
        if (beanId == null || beanId.trim().isEmpty()) {
            beanId = beanClass.getSimpleName();
        }
        return new BeanDef(beanId, beanClassName, beanClass);
    }

    private void buildBeanFromBeanDef(BeanDef beanDef) {
        Class<?> beanClass = beanDef.getBeanClass();
        Object beanInstance;
        try {
            beanInstance = beanClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to create bean for class:[" + beanClass + "]", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Please check whether reflection usage is allowed in " +
                    "security policy", e);
        }

        idToBeanMap.put(beanDef.getId(), beanInstance);
    }

}
