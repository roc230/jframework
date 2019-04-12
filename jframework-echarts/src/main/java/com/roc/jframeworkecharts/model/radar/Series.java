package com.roc.jframeworkecharts.model.radar;

import com.roc.jframework.basic.ext.NameValue;
import com.roc.jframeworkecharts.model.base.BasicItemStyle;
import com.roc.jframeworkecharts.model.base.BasicLabel;
import com.roc.jframeworkecharts.model.base.BasicNormal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Series implements Serializable {
    private String name = "";
    private String type = "radar";
    private List<NameValue<String,List<Object>>> data;
    private BasicItemStyle itemStyle = new BasicItemStyle(new BasicNormal(new BasicLabel(false)));

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

    public List<NameValue<String, List<Object>>> getData() {
        return data;
    }

    public void setData(List<NameValue<String, List<Object>>> data) {
        this.data = data;
    }

    public BasicItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(BasicItemStyle itemStyle) {
        this.itemStyle = itemStyle;
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
        public Builder data(NameValue<String,List<Object>>... keyvalue){
            this.series.setData(Arrays.asList(keyvalue));
            return this;
        }
        public Builder addData(NameValue<String,List<Object>>... keyvalue){
            if(this.series.getData() == null){
                this.series.setData(new ArrayList<>());
            }
            this.series.getData().addAll(Arrays.asList(keyvalue));
            return this;
        }
        public Builder showData(Boolean show){
            this.series.getItemStyle().getNormal().getLabel().setShow(show);
            return this;
        }
        public Series build(){
            return this.series;
        }
    }
}
