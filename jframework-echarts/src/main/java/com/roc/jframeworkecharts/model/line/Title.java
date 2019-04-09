package com.roc.jframeworkecharts.model.line;

import java.io.Serializable;

public class Title implements Serializable {

    private String text = "";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
