package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.web.rightmgr.entity.SysPermission;
import com.roc.jframework.web.rightmgr.service.IPermissionService;
import com.roc.jframework.web.rightmgr.vo.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/permission")
@Controller
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/account/{id}")
    @ResponseBody
    public List<PermissionVO> getPermissionsByAccountId(@PathVariable("id") String accountId){
        List<SysPermission> ps = this.permissionService.getPermissionsByAccountId(accountId);
        List<PermissionVO> vos = new ArrayList<>();
        for(SysPermission p : ps){
            PermissionVO vo = new PermissionVO();
            vo.setId(p.getId());
            vo.setName(p.getMenu().getName());
            vos.add(vo);
        }
        return vos;
    }

    @GetMapping("/role/{id}")
    @ResponseBody
    public List<PermissionVO> getPermissionByRoleId(@PathVariable("id") String roleId){
        List<SysPermission> ps = this.permissionService.getPermissionsByRoleId(roleId);
        List<PermissionVO> vos = new ArrayList<>();
        for(SysPermission p : ps){
            PermissionVO vo = new PermissionVO();
            vo.setId(p.getId());
            vo.setName(p.getMenu().getName());
            vos.add(vo);
        }
        return vos;
    }
}
