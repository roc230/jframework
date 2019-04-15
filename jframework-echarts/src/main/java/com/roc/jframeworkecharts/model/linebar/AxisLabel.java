package com.roc.jframeworkecharts.model.linebar;

import java.io.Serializable;

public class AxisLabel implements Serializable {
    private String formatter = "";

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }
}
