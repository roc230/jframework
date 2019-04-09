package com.roc.jframework.web.echart.controller;

import com.roc.jframeworkecharts.model.bar.BarOption;
import com.roc.jframeworkecharts.model.bar.Dataset;
import com.roc.jframeworkecharts.model.bar.Source;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.awt.SunHints;

import java.util.LinkedHashMap;

@RequestMapping(value = "/echart")
@Controller
public class BarController {

    @RequestMapping(value = "/bar")
    @ResponseBody
    public BarOption bar(){

        Dataset dataset = new Dataset();
        Source source = new Source();
        source.put("product", "2015").put("product", "2016").put("product", "2017");
//        dataset.addSource((LinkedHashMap<String, Object>) source);

        BarOption barOption = new BarOption();
//        barOption.setDataset();
        return barOption;
    }
}
