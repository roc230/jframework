package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.web.rightmgr.vo.EmployeeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    public List<EmployeeVO> getPage(){
        return null;
    }
}
