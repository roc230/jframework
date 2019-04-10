package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;

public abstract class BasicTooltip implements Serializable {
    private String trigger = "";

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
