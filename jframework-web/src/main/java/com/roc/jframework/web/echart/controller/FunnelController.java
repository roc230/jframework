package com.roc.jframework.web.echart.controller;

import com.roc.jframework.basic.ext.NameValue;
import com.roc.jframeworkecharts.model.funnel.FunnelOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/echart")
@Controller
public class FunnelController {

    @RequestMapping(value = "/funnel")
    @ResponseBody
    public FunnelOption funnel(){

        FunnelOption option = new FunnelOption.Builder()
                .title().text("漏斗图").subtext("纯属虚构").endTitle()
                .tooltip().formatter("{a} <br/>{b} : {c}%").trigger("item").endTooltip()
                .legend().addData("展现","点击","访问","咨询","订单").endLegend()
                .calculable(true)
                .series().name("漏斗图").type("funnel").width("40%").showData(true).addData(
                        new NameValue("访问", 60),
                        new NameValue("咨询", 40),
                        new NameValue("订单", 20),
                        new NameValue("点击", 80),
                        new NameValue("展现", 100)).endSeries()
                .build();



        return option;
    }
}
