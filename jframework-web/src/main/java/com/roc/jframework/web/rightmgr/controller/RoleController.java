package com.roc.jframework.web.rightmgr.controller;

import com.roc.jframework.web.rightmgr.entity.SysRole;
import com.roc.jframework.web.rightmgr.service.IRoleService;
import com.roc.jframework.web.rightmgr.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public String saveRole(@RequestBody RoleVO roleVO){
        SysRole role = new SysRole.Builder()
                .id(roleVO.getId())
                .enable(roleVO.getEnable())
                .name(roleVO.getName())
                .description(roleVO.getDescription())
                .latestOperateTime(roleVO.getLatestUpdateTime())
                .build();
        this.roleService.saveRole(role, roleVO.getOperatorId(), Arrays.asList(roleVO.getPermissionNames()));
        return "";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateRole(@RequestBody RoleVO roleVO){
        SysRole role = new SysRole();
        role.setId(roleVO.getId());
        role.setName(roleVO.getName());
        role.setDescription(roleVO.getDescription());
        this.roleService.updateRole(role, roleVO.getOperatorId(), Arrays.asList(roleVO.getPermissionNames()));
        return "success";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteRole(@RequestParam("id") String roleId){
        this.roleService.deleteRole(roleId, null);
        return "success";
    }

    @RequestMapping(value = "/page/{pageNum}/{pageSize}")
    @ResponseBody
    public Page getRolesByPage(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){
        Page p = this.roleService.getRolesByPage(pageNum, pageSize);
        List<SysRole> roles = p.getContent();
        for(SysRole r : roles){
            r.setAccounts(null);
            r.setPermissions(null);
        }
        return p;
    }
}
