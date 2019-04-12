package com.roc.jframeworkecharts.model.line;

import java.io.Serializable;

public class Normal implements Serializable {
    private Label label;

    public Normal(){}

    public Normal(Label label){
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
