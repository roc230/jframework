package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;

public class BasicNormal implements Serializable {

    private BasicLabel label;

    public BasicNormal(){}

    public BasicNormal(BasicLabel label){
        this.label = label;
    }

    public BasicLabel getLabel() {
        return label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }
}
