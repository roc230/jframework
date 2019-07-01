package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.web.rightmgr.dao.ISysPermissionDAO;
import com.roc.jframework.web.rightmgr.entity.SysPermission;
import com.roc.jframework.web.rightmgr.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private ISysPermissionDAO sysPermissionDAO;

    @Override
    public List<SysPermission> getPermissionsByAccountId(String accountId) {
        return this.sysPermissionDAO.getByAccountId(accountId);
    }

    @Override
    public List<SysPermission> getPermissionsByRoleId(String roleId) {
        return this.sysPermissionDAO.getByRoleId(roleId);
    }

    @Override
    public List<SysPermission> getByNames(String... names) {
        return this.sysPermissionDAO.getByNames(Arrays.asList(names));
    }
}
