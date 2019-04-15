package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicYAxis;

public class YAxis extends BasicYAxis {
    public static class Builder{
        private YAxis yAxis;
        private BarOption.Builder optionBuilder;

        public Builder(){
            this.yAxis = new YAxis();
        }

        public Builder(BarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
            this.yAxis = new YAxis();
        }

        public Builder type(String type){
            this.yAxis.setType(type);
            return this;
        }

        public BarOption.Builder endYAxis(){
            this.optionBuilder.getOption().addYAxis(this.yAxis);
            return this.optionBuilder;
        }

    }
}
