package com.roc.jframeworkecharts.model.radar;

import java.io.Serializable;

public class Indicator implements Serializable {
    private String text = "";
    private Object max = 0;

    public Indicator(){}

    public Indicator(String text, Object max){
        this.text = text;
        this.max = max;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getMax() {
        return max;
    }

    public void setMax(Object max) {
        this.max = max;
    }

    public static class Builder{
        private Indicator indicator = new Indicator();

        public Builder text(String text){
            this.indicator.setText(text);
            return this;
        }
        public Builder max(Object max){
            this.indicator.setMax(max);
            return this;
        }
        public Indicator build(){
            return this.indicator;
        }
    }
}
