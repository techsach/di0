package com.techsach.di0.beans.support;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlBeanFactory extends AbstractBeanFactory {

    public XmlBeanFactory(String xmlFilePath) {
        loadBeansFromXmlFile(xmlFilePath);
    }

    private void loadBeansFromXmlFile(String xmlFilePath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error while loading beans from file:[" + xmlFilePath + "]", e);
        }
        parseBeansFromXml(builder, xmlFilePath);
    }

    private void parseBeansFromXml(DocumentBuilder builder, String xmlFilePath) {
        Document document;
        try {
            document = builder.parse(xmlFilePath);
        } catch (SAXException e) {
            throw new RuntimeException("Error while parsing xml file:[" + xmlFilePath + "] for beans.", e);
        } catch (IOException e) {
            throw new RuntimeException("Unable to access xml file at:[" + xmlFilePath +"]", e);
        }
        NodeList beanNodeList = document.getElementsByTagName("bean");
        parseBeansFromNodeList(beanNodeList);
    }

    private void parseBeansFromNodeList(NodeList beanNodeList) {
    }

}
