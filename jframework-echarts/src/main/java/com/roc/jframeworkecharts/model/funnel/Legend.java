package com.roc.jframeworkecharts.model.funnel;

import com.roc.jframeworkecharts.model.base.BasicLegend;

import java.util.ArrayList;
import java.util.List;

public class Legend extends BasicLegend {

    public static final class Builder{
        private FunnelOption.Builder optionBuilder;
        private Legend legend = new Legend();

        public Builder(FunnelOption.Builder optionBuilder){
            this.optionBuilder = optionBuilder;
        }

        public Builder data(List<String> data){
            this.legend.setData(data);
            return this;
        }
        public Builder addData(String... data){
            if(this.legend.getData() == null){
                this.legend.setData(new ArrayList<>());
            }
            for(String d : data){
                this.legend.getData().add(d);
            }
            return this;
        }
        public FunnelOption.Builder endLegend(){
            this.optionBuilder.setLegend(this.legend);
            return this.optionBuilder;
        }
    }
}
