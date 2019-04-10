package com.roc.jframework.basic.ext;

import java.io.Serializable;

public class NameValue<K,V> implements Serializable {
    private K name;
    private V value;
    public NameValue(){}
    public NameValue(K name, V value){
        this.name = name;
        this.value = value;
    }

    public K getName() {
        return name;
    }

    public void setName(K name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
