package com.roc.jframeworkecharts.model.line;

import com.roc.jframework.basic.ext.HashMapExt;

import java.io.Serializable;
import java.time.format.TextStyle;

public class Label implements Serializable {
    private Boolean show = true;
    private String position = "top";
    private HashMapExt textStyle = new HashMapExt();

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public HashMapExt getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(HashMapExt textStyle) {
        this.textStyle = textStyle;
    }

    public static class Builder{
        private Label label = new Label();
        public Builder show(Boolean show){
            this.label.setShow(show);
            return this;
        }
        public Builder position(String position){
            this.label.setPosition(position);
            return this;
        }
        public Builder addTextStyle(String name, Object value){
            this.label.getTextStyle().putE(name, value);
            return this;
        }
        public Label build(){
            return this.label;
        }
    }
}
