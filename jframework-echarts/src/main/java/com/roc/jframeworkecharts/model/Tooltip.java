package com.roc.jframeworkecharts.model;

import java.io.Serializable;

public class Tooltip implements Serializable {

    private Boolean show = true;
    private String trigger = "";
    private String formatter = "";

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
