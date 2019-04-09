package com.roc.jframeworkecharts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XAxis implements Serializable {
    private String type;
    private List<String> data;
    private Boolean boundaryGap = false;

    public XAxis(){
        data = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public List<String> getData() {
        return data;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public void addData(String str){
        this.data.add(str);
    }

    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }
}
