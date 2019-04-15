package com.roc.jframeworkecharts.model.linebar;

import com.roc.jframeworkecharts.model.base.BasicYAxis;

public class YAxis extends BasicYAxis {
    private AxisLabel axisLabel;
    private String name = "";

    public AxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder{
        private YAxis yAxis;
        private LineBarOption.Builder optionBuilder;
        public Builder(LineBarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
            this.yAxis = new YAxis();
        }
        public Builder type(String type){
            this.yAxis.setType(type);
            return this;
        }
        public Builder name(String name){
            this.yAxis.setName(name);
            return this;
        }
        public Builder formatter(String formatter){
            if(this.yAxis.getAxisLabel() == null){
                this.yAxis.setAxisLabel(new AxisLabel());
            }
            this.yAxis.getAxisLabel().setFormatter(formatter);
            return this;
        }
        public LineBarOption.Builder endYAxis(){
            this.optionBuilder.getOption().addYAxis(this.yAxis);
            return this.optionBuilder;
        }
    }
}
