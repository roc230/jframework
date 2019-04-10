package com.roc.jframework.web.echart.controller;

import com.roc.jframework.basic.ext.NameValue;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframeworkecharts.model.radar.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/echart")
@Controller
public class RadarController {

    @RequestMapping(value = "/radar")
    @ResponseBody
    public RadarOption radar(){

        RadarOption option = new RadarOption.Builder()
                .title(new Title.Builder().text("预算 vs 开销（Budget vs spending）").subtext("纯属虚构").build())
                .tooltip(new Tooltip.Builder().trigger("axis").build())
                .legend(new Legend.Builder().orient("vertical").x("right").y("bottom").data("预算分配（Allocated Budget）","实际开销（Actual Spending）").build())
                .calculable(true)
                .polar(new Polar.Builder()
                        .addIndicator(new Indicator.Builder().text("销售（sales）").max(6000).build())
                        .addIndicator(new Indicator.Builder().text("管理（Administration）").max(16000).build())
                        .addIndicator(new Indicator.Builder().text("信息技术（Information Techology）").max(30000).build())
                        .addIndicator(new Indicator.Builder().text("客服（Customer Support）").max(38000).build())
                        .addIndicator(new Indicator.Builder().text("研发（Development）").max(52000).build())
                        .addIndicator(new Indicator.Builder().text("市场（Marketing）").max(25000).build())
                        .build())
                .series(new Series.Builder().name("预算 vs 开销（Budget vs spending）").type("radar")
                        .addData(new NameValue<>("预算分配（Allocated Budget）", ListUtils.newArrayList(new Object[]{4300, 10000, 28000, 35000, 50000, 19000})))
                        .addData(new NameValue<>("实际开销（Actual Spending）", ListUtils.newArrayList(new Object[]{5000, 14000, 28000, 31000, 42000, 21000})))
                        .build())
                .build();



        return option;
    }
}
