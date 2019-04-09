package com.roc.jframeworkweb;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframeworkecharts.builder.LineBuilder;
import com.roc.jframeworkecharts.model.Option;
import org.junit.Test;

import java.util.List;

public class EchartsTest {

    @Test
    public void teset(){
        List<String> xaxis = ListUtils.newArrayList(new String[]{"衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"});
        List<Integer> series = ListUtils.newArrayList(new Integer[]{5, 20, 40, 10, 10, 20});

        Option option = LineBuilder.create()
                .tooltip().show(true).endTooptip()
                .legend().addData("销量").endLengend()
                .xAxis().type("category").data(xaxis).endAXis()
                .yAxis().type("value").endYAxis()
                .series().name("销量").type("bar").data(series).endSeries()
                .build();

        String json = JsonUtils.toString(option);
        System.out.println(json);
    }
}
