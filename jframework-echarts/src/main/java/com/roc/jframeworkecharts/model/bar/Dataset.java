package com.roc.jframeworkecharts.model.bar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Dataset implements Serializable {

    private List<LinkedHashMap<String, Object>> source;

    public List<LinkedHashMap<String, Object>> getSource() {
        return source;
    }

    public void setSource(List<LinkedHashMap<String, Object>> source) {
        this.source = source;
    }

    public Dataset addSource(Source source){
        if(this.source == null){
            this.source = new ArrayList<LinkedHashMap<String, Object>>();
        }
//        this.source.add((LinkedHashMap<String, Object>) source);
        return this;
    }
}
