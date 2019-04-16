package com.techsach.di0.beans.support;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.matchers.InstanceOf;

public class TestXmlBeanFactory {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testXmlBeanFactory() {
        String xmlFile = "beans.xml";
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(xmlFile);
        TestBean testBean = xmlBeanFactory.getBean("bean");
        Assert.assertNotNull(testBean);
    }

    @Test
    public void testXmlBeanFactory_bean_not_found() {
        String xmlFile = "beans.xml";
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(xmlFile);

        expectedException.expect(new InstanceOf(RuntimeException.class));
        xmlBeanFactory.getBean("bean1");
    }

}