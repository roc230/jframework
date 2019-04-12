package com.roc.jframework.web.echart.controller;

import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframeworkecharts.model.line.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/echart")
@Controller
public class LineController {

    @RequestMapping("/line")
    @ResponseBody
    public LineOption line(){
        List<String> xaxis = ListUtils.newArrayList(new String[]{"衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"});
        List<Integer> series = ListUtils.newArrayList(new Integer[]{5, 20, 40, 10, 10, 20});
        List<Integer> series2 = ListUtils.newArrayList(new Integer[]{6, 27, 40, 20, 50, 40});

        LineOption option = LineOptionBuilder.create()
                .tooltip().trigger("axis").endTooltip()
                .legend().addData("销量").addData("销量2").endLegend()
                .xAxis().type("category").setData(xaxis).endXAxis()
                .yAxis().type("value").endYAxis()
                .series().name("销量").type("line").setData(series).showData(true).endSeries()
                .series().name("销量2").type("line").setData(series2).showData(true).endSeries()
                .grid().left("4%").right("4%").bottom("4%").endGrid()
                .title().text("折线图堆叠").endTitle()
                .build();
        return option;
    }

}
