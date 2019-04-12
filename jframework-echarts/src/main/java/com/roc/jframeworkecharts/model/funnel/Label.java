package com.roc.jframeworkecharts.model.funnel;

import com.roc.jframeworkecharts.model.base.BasicLabel;

public class Label extends BasicLabel {

    private String position = "inside";
    private String formatter = "{b}:{c}%";

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public Label(Boolean show, String position){
        this.setShow(show);
        this.position = position;
    }
}
