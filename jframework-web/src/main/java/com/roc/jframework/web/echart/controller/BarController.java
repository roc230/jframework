package com.roc.jframework.web.echart.controller;

import com.roc.jframework.basic.ext.HashMapExt;
import com.roc.jframeworkecharts.Echarts;
import com.roc.jframeworkecharts.model.bar.*;
import com.roc.jframeworkecharts.model.base.BasicToolbox;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/echart")
@Controller
public class BarController {

    @RequestMapping(value = "/bar")
    @ResponseBody
    public BarOption bar(){

        BarOption option = Echarts.barOptionBuilder()
                .title().text("某地区蒸发量和降水量").subtext("纯属虚构").endTitle()
                .legend().addData("蒸发量", "降水量").endLegend()
                .tooltip().trigger("axis").endTooltip()
                .xAxis().type("category").addData("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月").endXAxis()
                .yAxis().type("value").endYAxis()
                .series().markPoint(new HashMapExt().putE("type","max").putE("name","最大值"))
                        .markPoint(new HashMapExt().putE("type", "min").putE("name", "最小值"))
                        .name("蒸发量")
//                        .markLine("average", "平均值")
                        .type("bar")
                        .data(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3)
                        .showData(true)
                        .endSeries()
                .series().markPoint(new HashMapExt().putE("name","年最高").putE("value", 182.2)
                                        .putE("xAxis", 7).putE("yAxis", 183).putE("symbolSize", 18))
                        .markPoint(new HashMapExt().putE("name", "年最低").putE("value", 2.3))
//                        .markLine("average", "平均值")
                        .name("降水量")
                        .type("bar")
                        .data(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3)
                        .showData(true)
                        .endSeries()
                .build();


        return option;
    }
}
