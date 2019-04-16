package com.techsach.di0.beans.support;

import com.techsach.di0.beans.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory implements BeanFactory {

    protected Map<String, Object> idToBeanMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getBean(String id) {
        Object beanInstance = idToBeanMap.get(id);
        if (beanInstance == null) {
            throw new RuntimeException("Bean with id:[" + id + "] not found.");
        }
        return (T) beanInstance;
    }

}
