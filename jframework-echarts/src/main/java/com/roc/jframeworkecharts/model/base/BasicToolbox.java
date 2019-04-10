package com.roc.jframeworkecharts.model.base;

import java.io.Serializable;

public class BasicToolbox implements Serializable {
    private Boolean show = true;
    private BasicFeature feature = new BasicFeature();

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public BasicFeature getFeature() {
        return feature;
    }

    public void setFeature(BasicFeature feature) {
        this.feature = feature;
    }
}
