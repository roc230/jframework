package com.roc.jframeworkecharts.model.radar;

import com.roc.jframeworkecharts.model.base.BasicTitle;

public class Title extends BasicTitle {

    public static class Builder{
        private Title title = new Title();
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
