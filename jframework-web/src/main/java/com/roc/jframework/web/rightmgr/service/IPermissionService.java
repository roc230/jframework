package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.web.rightmgr.entity.SysPermission;

import java.util.List;

public interface IPermissionService {

    /**
     * 获取某帐号的所有权限
     * @param accountId
     * @return
     */
    List<SysPermission> getPermissionsByAccountId(String accountId);

    /**
     * 获取某角色的权限
     * @param roleId
     * @return
     */
    List<SysPermission> getPermissionsByRoleId(String roleId);

    List<SysPermission> getByNames(String... names);
}
