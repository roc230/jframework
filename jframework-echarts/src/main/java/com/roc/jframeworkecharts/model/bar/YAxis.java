package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicYAxis;

public class YAxis extends BasicYAxis {
    public static class Builder{
        private YAxis yAxis;
        public Builder(){
            this.yAxis = new YAxis();
        }
        public Builder type(String type){
            this.yAxis.setType(type);
            return this;
        }
        public YAxis build(){
            return this.yAxis;
        }
    }
}
