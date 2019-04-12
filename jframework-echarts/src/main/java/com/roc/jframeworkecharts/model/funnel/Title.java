package com.roc.jframeworkecharts.model.funnel;

import com.roc.jframeworkecharts.model.base.BasicTitle;

public class Title extends BasicTitle {

    public static final class Builder{
        private FunnelOption.Builder optionBuilder;
        Title title = new Title();

        public Builder(FunnelOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
        }

        public Builder text(String text){
            this.title.setText(text);
            return this;
        }
        public Builder subtext(String subtext){
            this.title.setSubtext(subtext);
            return this;
        }
        public FunnelOption.Builder endTitle(){
            this.optionBuilder.setTitle(this.title);
            return this.optionBuilder;
        }
    }
}
