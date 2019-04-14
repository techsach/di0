package com.techsach.di0.beans;

public class Bean {

    private final String id;
    private final String className;

    protected Bean(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }
}
