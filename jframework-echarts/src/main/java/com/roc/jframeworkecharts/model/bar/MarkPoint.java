package com.roc.jframeworkecharts.model.bar;

import com.roc.jframework.basic.ext.HashMapExt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MarkPoint {

    private List<HashMapExt<String,Object>> data;

    public List<HashMapExt<String, Object>> getData() {
        return data;
    }

    public void setData(List<HashMapExt<String, Object>> data) {
        this.data = data;
    }

    public MarkPoint addData(HashMapExt<String,Object> map){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        this.data.add(map);
        return this;
    }

    public static class Builder{
        private MarkPoint markPoint = new MarkPoint();
        public Builder addData(HashMapExt dataMap){
            this.markPoint.addData(dataMap);
            return this;
        }
        public MarkPoint build(){
            return this.markPoint;
        }
    }
}
