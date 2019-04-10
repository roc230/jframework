package com.roc.jframeworkecharts.model.bar;

import com.roc.jframework.basic.ext.HashMapExt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MarkLine implements Serializable {

    private List<HashMap<String,Object>> data;

    public List<HashMap<String, Object>> getData() {
        return data;
    }

    public void setData(List<HashMap<String, Object>> data) {
        this.data = data;
    }

    public MarkLine addData(HashMap<String,Object> map){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        this.data.add(map);
        return this;
    }

    public static class Builder{
        private MarkLine markLine = new MarkLine();
        public Builder addData(HashMapExt dataMap){
            this.markLine.addData(dataMap);
            return this;
        }
        public MarkLine build(){
            return this.markLine;
        }
    }
}
