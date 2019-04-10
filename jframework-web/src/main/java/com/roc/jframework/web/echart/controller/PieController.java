package com.roc.jframework.web.echart.controller;

import com.roc.jframework.basic.ext.NameValue;
import com.roc.jframeworkecharts.model.pie.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/echart")
@Controller
public class PieController {

    @RequestMapping(value = "/pie")
    @ResponseBody
    public PieOption pie(){

        PieOption option = new PieOption.Builder()
                .title(new Title.Builder()
                        .text("某站点用户访问来源")
                        .subtext("纯属虚构")
                        .x("center")
                        .build())
                .tooltip(new Tooltip.Builder()
                        .formatter("{a}<br/>{b}:{c}({d}%)")
                        .trigger("item")
                        .build())
                .legend(new Legend.Builder()
                        .data("直接访问","邮件营销","联盟广告","视频广告","搜索引擎")
                        .orient("vertical")
                        .x("left")
                        .build())
                .series(new Series.Builder()
                        .center("50%","60%")
                        .name("访问来源")
                        .radius("55%")
                        .type("pie")
                        .data(new NameValue("直接访问", 335),
                                new NameValue("邮件营销", 310),
                                new NameValue("联盟广告",234),
                                new NameValue("视频广告", 135),
                                new NameValue("探索引擎", 1548))
                        .build())
                .build();



        return option;
    }
}
