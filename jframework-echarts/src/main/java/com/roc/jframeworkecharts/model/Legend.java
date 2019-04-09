package com.roc.jframeworkecharts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Legend implements Serializable {

    private List<String> data;
    private String orient = "vertical";
    private String left = "";

    public Legend(){
        this.data = new ArrayList<>();
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public void add(String str){
        this.data.add(str);
    }

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }
}
