package com.roc.jframeworkecharts.model.pie;

import com.roc.jframework.basic.ext.KeyValue;
import com.roc.jframework.basic.ext.NameValue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Series implements Serializable {
    private String name = "";
    private String type = "pie";
    private String radius = "";
    private List<String> center;
    private List<NameValue> data;

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

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public List<String> getCenter() {
        return center;
    }

    public void setCenter(List<String> center) {
        this.center = center;
    }

    public List<NameValue> getData() {
        return data;
    }

    public void setData(List<NameValue> data) {
        this.data = data;
    }

    public static class Builder{
        private Series series = new Series();
        public Builder name(String name){
            this.series.setName(name);
            return this;
        }
        public Builder type(String type){
            this.series.setType(type);
            return this;
        }
        public Builder radius(String radius){
            this.series.setRadius(radius);
            return this;
        }
        public Builder center(String... center){
            this.series.setCenter(Arrays.asList(center));
            return this;
        }
        public Builder data(NameValue... keyvalue){
            this.series.setData(Arrays.asList(keyvalue));
            return this;
        }
        public Series build(){
            return this.series;
        }
    }
}
