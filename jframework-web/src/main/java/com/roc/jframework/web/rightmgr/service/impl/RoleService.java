package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.rightmgr.dao.ISysAccountDAO;
import com.roc.jframework.web.rightmgr.dao.ISysPermissionDAO;
import com.roc.jframework.web.rightmgr.dao.ISysRoleDAO;
import com.roc.jframework.web.rightmgr.entity.SysPermission;
import com.roc.jframework.web.rightmgr.entity.SysRole;
import com.roc.jframework.web.rightmgr.service.IRoleService;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public SysRole saveRole(SysRole role, String operatorId, List<String> permissionNames) {

        if(role == null){

        }

        if(StringUtils.isNullOrEmpty(operatorId)){
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

//        SysAccount account = this.sysAccountDAO.findById(operatorId).get();
        List<SysPermission> permissions = this.sysPermissionDAO.getByNames(permissionNames);

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
        this.sysRoleDAO.deleteById(roleId);
    }

    @Override
    public Page<SysRole> getRolesByPage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return this.sysRoleDAO.findAll(pageable);
    }

    @Override
    public SysRole updateRole(SysRole role, String operatorId, List<String> permissionNames) {

        List<SysPermission> permissions = this.sysPermissionDAO.getByNames(permissionNames);

        SysRole old = this.sysRoleDAO.getOne(role.getId());
        old.setName(role.getName());
        old.setEnable(true);
        old.setLatestOperateTime(new Date());
        old.setPermissions(permissions);
        old.setDescription(role.getDescription());
        return this.sysRoleDAO.save(old);
    }
}
