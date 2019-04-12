package com.roc.jframeworkecharts.model.line;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Series<T> implements Serializable {
    private String name = "";
    private String type = "line";
    private String stack = "";
    private List<T> data;
    private ItemStyle itemStyle = new ItemStyle(new Normal(new Label.Builder().show(false).build()));

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

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Series addData(T data){
        if(this.data == null){
            this.data = new ArrayList<>();
        }
        this.data.add(data);
        return this;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }

    public void setItemStyle(Boolean show, String position, String color){
        this.itemStyle = new ItemStyle(new Normal(new Label.Builder().show(show).position(position).addTextStyle("color", color).build()));
    }

    public void showData(Boolean show){
        if(show == true){
            this.itemStyle.getNormal().getLabel().setShow(true);
        }
    }
}
