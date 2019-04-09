package com.roc.jframeworkecharts.model.line;

import java.io.Serializable;

public class Tooltip implements Serializable {

    private String trigger = "";

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
