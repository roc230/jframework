package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.rightmgr.dao.ISysAccountDAO;
import com.roc.jframework.web.rightmgr.dao.ISysPermissionDAO;
import com.roc.jframework.web.rightmgr.dao.ISysRoleDAO;
import com.roc.jframework.web.rightmgr.entity.SysPermission;
import com.roc.jframework.web.rightmgr.entity.SysRole;
import com.roc.jframework.web.rightmgr.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class RoleService implements IRoleService {

    @Autowired
    private ISysAccountDAO sysAccountDAO;
    @Autowired
    private ISysPermissionDAO sysPermissionDAO;
    @Autowired
    private ISysRoleDAO sysRoleDAO;

    @Transactional(readOnly = true)
    @Override
    public List<SysRole> getRoles() {
        return this.sysRoleDAO.findAll();
    }

    @Override
    public SysRole saveRole(SysRole role, String operatorId, List<String> permissionIds) {

        if(role == null){

        }

        if(StringUtils.isNullOrEmpty(operatorId)){
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

//        SysAccount account = this.sysAccountDAO.findById(operatorId).get();
        List<SysPermission> permissions = this.sysPermissionDAO.findSysPermissionsByIdIn(permissionIds);

        //ID为空，是新创建
        if(StringUtils.isNullOrEmpty(role.getId())){
//            role.setOperator(account);
            role.setPermissions(permissions);
            role.setEnable(true);
            role.setLatestOperateTime(new Date());
            return this.sysRoleDAO.save(role);
        }

        SysRole sysRole = this.sysRoleDAO.findById(role.getId()).get();



        sysRole.setLatestOperateTime(new Date());
        sysRole.setEnable(true);
        sysRole.setPermissions(permissions);
        sysRole.setOperator(null);
        sysRole.setName(role.getName());
        return this.sysRoleDAO.save(sysRole);

    }

    @Override
    public void deleteRole(String roleId, String operatorId) {
        if(StringUtils.isNullOrEmpty(roleId)){
            return ;
        }
        this.sysRoleDAO.disable(roleId);
    }

}
