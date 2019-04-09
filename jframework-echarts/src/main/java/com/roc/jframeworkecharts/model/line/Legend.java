package com.roc.jframeworkecharts.model.line;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Legend implements Serializable {

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public Legend addData(String data){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        this.data.add(data);
        return this;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
