package com.roc.jframeworkecharts.model;

import java.io.Serializable;

public class Emphasis implements Serializable {
    private Integer shadowBlur = 10;
    private Integer shadowOffsetX = 0;
    private String shadowColor = "";

    public Integer getShadowBlur() {
        return shadowBlur;
    }

    public void setShadowBlur(Integer shadowBlur) {
        this.shadowBlur = shadowBlur;
    }

    public Integer getShadowOffsetX() {
        return shadowOffsetX;
    }

    public void setShadowOffsetX(Integer shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
    }

    public String getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(String shadowColor) {
        this.shadowColor = shadowColor;
    }
}
