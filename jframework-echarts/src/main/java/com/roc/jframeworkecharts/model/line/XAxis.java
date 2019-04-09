package com.roc.jframeworkecharts.model.line;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XAxis implements Serializable {
    private String type  = "";
    private Boolean boundaryGap = false;

    private List<String> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public XAxis addData(String data){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        this.data.add(data);
        return this;
    }
}
