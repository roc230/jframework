package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicXAxis;

public class XAxis extends BasicXAxis {

    public static class Builder{
        private XAxis xAxis;
        private BarOption.Builder optionBuilder;

        public Builder(){
            this.xAxis = new XAxis();
        }

        public Builder(BarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
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

        public BarOption.Builder endXAxis(){
            this.optionBuilder.getOption().addXAxis(this.xAxis);
            return this.optionBuilder;
        }

    }

}
