package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;

public class BasicItemStyle implements Serializable {

    private BasicNormal normal;

    public BasicItemStyle(){}

    public BasicItemStyle(BasicNormal normal){
        this.normal = normal;
    }

    public BasicNormal getNormal() {
        return normal;
    }

    public void setNormal(BasicNormal normal) {
        this.normal = normal;
    }
}
