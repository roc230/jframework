package com.roc.jframeworkecharts.model.bar;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

public class Source extends LinkedHashMap<String, List<Object>> implements Serializable {

    public Source put(String key, Object value){
        this.put(key, value);
        return this;
    }

    public Source put(String key, Object... values){
        for(Object obj : values){
            this.put(key, obj);
        }
        return this;
    }
}
