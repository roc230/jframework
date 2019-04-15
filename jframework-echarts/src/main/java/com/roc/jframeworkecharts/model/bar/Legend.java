package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicLegend;

public class Legend extends BasicLegend {

    public static class Builder{
        private Legend legend;
        private BarOption.Builder optionBuilder;

        public Builder(){
            this.legend = new Legend();
        }

        public Builder(BarOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
            this.legend = new Legend();
        }

        public Builder addData(String data){
            this.legend.addData(data);
            return this;
        }

        public Builder addData(String... data){
            for(String d : data){
                this.legend.addData(d);
            }
            return this;
        }

        public BarOption.Builder endLegend(){
            this.optionBuilder.getOption().setLegend(this.legend);
            return this.optionBuilder;
        }

    }
}
