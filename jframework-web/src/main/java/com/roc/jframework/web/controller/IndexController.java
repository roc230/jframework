package com.roc.jframework.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index(){
//        return "index";
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("name", "roc");
        return mav;
    }


}
