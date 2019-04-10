package com.roc.jframework.web.echart.controller;

import com.roc.jframework.basic.ext.HashMapExt;
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

        Title title = new Title.Builder()
                .text("某地区蒸发量和降水量")
                .subtext("纯属虚构")
                .build();
        Tooltip tooltip = new Tooltip.Builder()
                .trigger("axis")
                .build();
        Legend legend = new Legend.Builder()
                .addData("蒸发量", "降水量")
                .build();
        XAxis xAxis = new XAxis.Builder()
                .type("category")
                .addData(new String[]{"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"})
                .build();
        YAxis yAxis = new YAxis.Builder()
                .type("value")
                .build();

        MarkPoint markPoint = new MarkPoint.Builder()
                .addData(new HashMapExt().putE("name", "年最高").putE("value", 182.2).putE("xAxis", 7).putE("yAxis", 183).putE("symboleSize", 1))
                .addData(new HashMapExt().putE("name", "年最低").putE("value", 2.3).putE("xAxis", 11).putE("yAxis", 3))
                .build();

        MarkLine markLine = new MarkLine.Builder()
                .addData(new HashMapExt().putE("type","average").putE("name", "平均值"))
                .build();

        Series series = new Series.Builder()
                .name("蒸发量")
                .type("bar")
                .data(2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3)
                .markPoint(new MarkPoint.Builder()
                        .addData(new HashMapExt().putE("type", "max").putE("name", "最大值"))
                        .addData(new HashMapExt().putE("type", "min").putE("name", "最小值")).build())
                .markLine(new MarkLine.Builder().addData(new HashMapExt().putE("type", "average").putE("name", "平均值")).build())
                .build();
        Series series2 = new Series.Builder()
                .name("降水量")
                .type("bar")
                .data(2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3)
                .markPoint(new MarkPoint.Builder()
                        .addData(new HashMapExt().putE("name", "年最高").putE("value", 182.2).putE("xAxis", 7).putE("yAxis", 183).putE("symbolSize", 1))
                        .addData(new HashMapExt().putE("name","年最低").putE("value", 2.3).putE("xAxis", 11).putE("yAxis", 3)).build())
                .markLine(new MarkLine.Builder().addData(new HashMapExt().putE("type", "average").putE("name", "平均值")).build())
                .build();

        BasicToolbox toolbox = new BasicToolbox();
        toolbox.setShow(true);
        toolbox.getFeature().getMark().putE("show", true);
        toolbox.getFeature().getDataView().putE("show",true).putE("readOnly",false);
        toolbox.getFeature().getMagicType().putE("show", true).putE("type",new String[]{"line", "bar"});
        toolbox.getFeature().getRestore().putE("show", true);
        toolbox.getFeature().getSaveAsImage().putE("show", true);


        BarOption option = new BarOption()
                .setLegend(legend)
                .setTitle(title)
                .setTooltip(tooltip)
                .addXAxis(xAxis)
                .addYAxis(yAxis)
                .setToolbox(toolbox)
                .setCalculable(true)
                .addSeries(series)
                .addSeries(series2);


        return option;
    }
}
