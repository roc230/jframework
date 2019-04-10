package com.roc.jframeworkecharts.model.bar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dataset implements Serializable {

   private List<Source> source;

    public List<Source> getSource() {
        return source;
    }

    public void setSource(List<Source> source) {
        this.source = source;
    }

    public Dataset addSource(Source source){
        if(this.source == null){
            this.source = new ArrayList<>();
        }
        this.source.add(source);
        return this;
    }
}
