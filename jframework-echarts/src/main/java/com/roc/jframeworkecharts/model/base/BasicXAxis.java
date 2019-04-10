package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicXAxis implements Serializable {
    private String type  = "";
    private List<String> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public BasicXAxis addData(String... data){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        for(String d : data){
            this.data.add(d);
        }
        return this;
    }
}
