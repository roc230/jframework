package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;

public abstract class BasicTitle implements Serializable {

    private String text = "";
    private String subtext = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

}
