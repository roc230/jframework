package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicTitle;

public class Title extends BasicTitle {

    public static class Builder{
        private Title title;
        private BarOption.Builder optionBuilder;

        public Builder(){
            title = new Title();
        }

        public Builder(BarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
            this.title = new Title();
        }

        public Builder text(String text){
            this.title.setText(text);
            return this;
        }

        public Builder subtext(String subtext){
            this.title.setSubtext(subtext);
            return this;
        }

        public BarOption.Builder endTitle(){
            this.optionBuilder.getOption().setTitle(this.title);
            return this.optionBuilder;
        }

    }

}
