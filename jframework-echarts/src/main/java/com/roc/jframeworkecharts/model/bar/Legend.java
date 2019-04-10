package com.roc.jframeworkecharts.model.bar;

import com.roc.jframeworkecharts.model.base.BasicLegend;

public class Legend extends BasicLegend {

    public static class Builder{
        private Legend legend;
        public Builder(){
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
        public Legend build(){
            return this.legend;
        }
    }
}
