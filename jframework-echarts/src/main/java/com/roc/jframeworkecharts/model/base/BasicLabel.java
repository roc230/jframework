package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;

public class BasicLabel implements Serializable {
    private Boolean show = false;

    public BasicLabel(){}

    public BasicLabel(Boolean show){
        this.show = show;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
