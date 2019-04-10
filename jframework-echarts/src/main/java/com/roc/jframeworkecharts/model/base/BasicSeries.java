package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BasicSeries implements Serializable {
    private String name = "";
    private String type = "";
    private List data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public BasicSeries addData(Object... data){
        if(this.data == null){
            this.data = new ArrayList();
        }
        for(Object o : data){
            this.data.add(o);
        }
        return this;
    }
}
