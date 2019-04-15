package com.roc.jframeworkecharts.model.linebar;

import com.roc.jframeworkecharts.model.base.BasicSeries;

public class Series extends BasicSeries {
    private Integer yAxisIndex = 0;

    public Integer getyAxisIndex() {
        return yAxisIndex;
    }

    public void setyAxisIndex(Integer yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }

    public static final class Builder{
        private Series series;
        private LineBarOption.Builder optionBuilder;
        public Builder(LineBarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
            this.series = new Series();
        }

        public Builder name(String name){
            this.series.setName(name);
            return this;
        }

        public Builder type(String type){
            this.series.setType(type);
            return this;
        }

        public Builder yAxisIndex(Integer yAxisIndex){
            this.series.setyAxisIndex(yAxisIndex);
            return this;
        }

        public Builder data(Object... data){
            this.series.addData(data);
            return this;
        }
        public Builder showData(Boolean show){
            if(show == true){
                this.series.getItemStyle().getNormal().getLabel().setShow(true);
            }
            return this;
        }
        public LineBarOption.Builder endSeries(){
            this.optionBuilder.getOption().addSeries(this.series);
            return this.optionBuilder;
        }

    }
}
