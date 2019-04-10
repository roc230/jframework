package com.roc.jframeworkecharts.model.pie;

import com.roc.jframeworkecharts.model.base.BasicTitle;

public class Title extends BasicTitle {

    private String x = "";

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public static class Builder{
        private Title title = new Title();
        public Builder x(String x){
            this.title.setX(x);
            return this;
        }
        public Builder text(String text){
            this.title.setText(text);
            return this;
        }
        public Builder subtext(String subtext){
            this.title.setSubtext(subtext);
            return this;
        }
        public Title build(){
            return this.title;
        }
    }
}
