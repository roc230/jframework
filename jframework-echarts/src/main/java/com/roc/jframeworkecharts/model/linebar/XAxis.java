package com.roc.jframeworkecharts.model.linebar;

import com.roc.jframeworkecharts.model.base.BasicXAxis;

public class XAxis extends BasicXAxis {

    public static final class Builder{
        private XAxis xAxis = new XAxis();
        private LineBarOption.Builder optionBuilder;
        public Builder(LineBarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
            this.xAxis = new XAxis();
        }
        public Builder type(String type){
            this.xAxis.setType(type);
            return this;
        }
        public Builder data(String... data){
            this.xAxis.addData(data);
            return this;
        }
        public LineBarOption.Builder endXAxis(){
            this.optionBuilder.getOption().addXAxis(this.xAxis);
            return this.optionBuilder;
        }
    }
}
