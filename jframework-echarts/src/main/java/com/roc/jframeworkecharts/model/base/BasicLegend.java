package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicLegend implements Serializable {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public BasicLegend addData(String data){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        this.data.add(data);
        return this;
    }
}
