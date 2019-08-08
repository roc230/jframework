package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.basic.constants.ServiceResult;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.entity.ExeResult;
import com.roc.jframework.web.rightmgr.entity.SysEmployee;
import com.roc.jframework.web.rightmgr.service.IEmployeeService;
import com.roc.jframework.web.rightmgr.vo.EmployeeVO;
import com.roc.jframework.web.rightmgr.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/page")
    @ResponseBody
    public List<EmployeeVO> getPage(){
        return null;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public EmployeeVO get(@PathVariable("id") String employeeId){
        return null;
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveEmployee(@RequestBody EmployeeVO employeeVO){
        SysEmployee employee = new SysEmployee();
        employee.setEnable(true);
        employee.setName(employeeVO.getName());
        employee.setBirthday(employeeVO.getBirthday());
        employee.setJoinTime(employeeVO.getJoinTime());
        employee.setSex(employeeVO.getSex());

        String[] ids = employeeVO.getOrgIds() != null ? employeeVO.getOrgIds()[0] : new String[0];
        this.employeeService.add(employee, ids, employeeVO.getAccount());
        return "SUCCESS";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateEmployee(@RequestBody EmployeeVO employeeVO){
        return null;
    }

    @PostMapping("/delete")
    @ResponseBody
    public ExeResult deleteEmployee(@RequestParam("id") String employeeId){
        ExeResult r = this.employeeService.delete(employeeId);
        return r;
    }

    @GetMapping("/page/{pageNum}/{pageSize}")
    @ResponseBody
    public PageVO<EmployeeVO> getByPages(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        ExeResult<Page<SysEmployee>> page = this.employeeService.getPage(pageNum - 1, pageSize);
        PageVO<EmployeeVO> pageVO = new PageVO<EmployeeVO>(page.getObj()) {
            @Override
            public <K> EmployeeVO transform(K obj) {
                SysEmployee emp = (SysEmployee)obj;
                EmployeeVO vo = new EmployeeVO();
                vo.setId(emp.getId());
                vo.setName(emp.getName());
                if(StringUtils.isNullOrEmpty(emp.getSex())){
                    vo.setSex("");
                }else if (emp.getSex().equals("male")){
                    vo.setSex("男");
                }else if(emp.getSex().equals("female")){
                    vo.setSex("女");
                }
                String orgNames = "";
                orgNames += emp.getCompany() != null ? emp.getCompany().getName() : "";
                orgNames += emp.getDepartment() != null ? "/" + emp.getDepartment().getName() : "";
                orgNames += emp.getStation() != null ? "/" + emp.getStation().getName() : "";
                vo.setOrgNames(orgNames);
                vo.setAccount(emp.getAccount() != null ? emp.getAccount().getLoginName() : "");
                vo.setJoinTime(emp.getJoinTime());
                vo.setBirthday(emp.getBirthday());
                return vo;
            }
        };
        return pageVO;
    }
}
