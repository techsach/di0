package com.techsach.di0client;

import com.techsach.di0.beans.BeanFactory;
import com.techsach.di0.beans.support.TestBean;
import com.techsach.di0.beans.support.XmlBeanFactory;

public class Di0Client {

    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory("beans.xml");

        TestBean testBean = beanFactory.getBean("bean");

        System.out.println("Result:" + testBean.testBeanMethod());
    }

}
