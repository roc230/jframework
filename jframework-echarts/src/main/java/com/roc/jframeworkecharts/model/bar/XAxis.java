package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicXAxis;

public class XAxis extends BasicXAxis {

    public static class Builder{
        private XAxis xAxis;
        public Builder(){
            this.xAxis = new XAxis();
        }
        public Builder type(String type){
            this.xAxis.setType(type);
            return this;
        }
        public Builder addData(String... data){
            this.xAxis.addData(data);
            return this;
        }
        public XAxis build(){
            return this.xAxis;
        }
    }

}
