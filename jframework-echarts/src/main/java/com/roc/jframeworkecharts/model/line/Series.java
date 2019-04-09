package com.roc.jframeworkecharts.model.line;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Series<T> implements Serializable {
    private String name = "";
    private String type = "line";
    private String stack = "";
    private List<T> data;

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

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Series addData(T data){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        this.data.add(data);
        return this;
    }
}
