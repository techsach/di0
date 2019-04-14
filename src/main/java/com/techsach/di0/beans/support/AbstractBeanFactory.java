package com.techsach.di0.beans.support;

import com.techsach.di0.beans.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    protected Map<String, Object> idToBeanMap = new ConcurrentHashMap<>();

    public <T> T getBean(String id) {
        return (T) idToBeanMap.get(id);
    }
}
