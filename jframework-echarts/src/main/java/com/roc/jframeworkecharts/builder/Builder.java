package com.roc.jframeworkecharts.builder;

import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframeworkecharts.Echarts;
import com.roc.jframeworkecharts.model.Option;

import java.util.ArrayList;
import java.util.List;

public class Builder {

    public void dd(){

        List<String> xaxis = ListUtils.newArrayList(new String[]{"衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"});
        List<Integer> series = ListUtils.newArrayList(new Integer[]{5, 20, 40, 10, 10, 20});

        Option option = LineBuilder.create()
                .tooltip().show(true).endTooptip()
                .legend().addData("销量").endLengend()
                .xAxis().type("category").data(xaxis).endAXis()
                .yAxis().type("value").endYAxis()
                .series().name("销量").type("bar").data(series).endSeries()
                .build();

    }
}
