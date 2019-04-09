package com.roc.jframeworkecharts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Series<T> implements Serializable {
    private String name = "";
    private String type = "";
    private String stack = "";
    private List<T> data;
    private String radius = "";
    private List<String> center = new ArrayList<>();
//    private ItemStyle itemStyle;

    public Series(){
        this.data = new ArrayList<>();
    }

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void addData(T data) {
        this.data.add(data);
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
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

    public void addCenter(String center){
        if(this.center == null){
            this.center = new ArrayList<>();
        }
        this.center.add(center);
    }

//    public ItemStyle getItemStyle() {
//        return itemStyle;
//    }
//
//    public void setItemStyle(ItemStyle itemStyle) {
//        this.itemStyle = itemStyle;
//    }
}
