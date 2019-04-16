package com.techsach.di0.beans;

public class BeanDef {

    private final String id;
    private final String className;
    private Class<?> beanClass;

    public BeanDef(String id, String className, Class<?> beanClass) {
        this.id = id;
        this.className = className;
        this.beanClass = beanClass;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }
}
