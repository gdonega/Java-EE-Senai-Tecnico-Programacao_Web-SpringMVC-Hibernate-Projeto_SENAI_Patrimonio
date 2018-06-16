package org.adataq.jserializer.plugins.retrofit.models;


import org.adataq.jserializer.json.JfoObject;


public class ObjectWithFilter<T> {
    private T t;
    private JfoObject filter;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public JfoObject getFilter() {
        return filter;
    }

    public void setFilter(JfoObject filter) {
        this.filter = filter;
    }

    public ObjectWithFilter(T t, JfoObject filter) {
        this.t = t;
        this.filter = filter;
    }

    @Override
    public String toString() {
        return "ObjectWithFilter{" +
                "t=" + t +
                ", filter=" + filter +
                '}';
    }
}
