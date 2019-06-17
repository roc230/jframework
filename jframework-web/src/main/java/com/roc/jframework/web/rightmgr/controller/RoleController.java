package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.web.rightmgr.entity.SysRole;
import com.roc.jframework.web.rightmgr.service.IRoleService;
import com.roc.jframework.web.rightmgr.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/all")
    @ResponseBody
    public List<SysRole> getRoles(){
        return this.roleService.getRoles();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRole(@RequestBody RoleVO roleVO){
        SysRole role = new SysRole.Builder()
                .id(roleVO.getId())
                .enable(roleVO.getEnable())
                .name(roleVO.getName())
                .latestOperateTime(roleVO.getLatestUpdateTime())
                .build();
        this.roleService.saveRole(role, roleVO.getOperatorId(), Arrays.asList(roleVO.getPermissionIds()));
        return "";
    }
}
