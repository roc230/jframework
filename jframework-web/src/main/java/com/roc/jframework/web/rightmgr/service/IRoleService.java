package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.web.rightmgr.entity.SysRole;

import java.util.List;

public interface IRoleService {

    public List<SysRole> getRoles();

    /**
     * 保存角色
     * @param role
     * @param operatorId
     * @param permissionIds
     * @return
     */
    SysRole saveRole(SysRole role, String operatorId, List<String> permissionIds);

    /**
     * 删除角色
     * @param roleId 角色ID
     * @param operatorId 操作人ID
     */
    void deleteRole(String roleId, String operatorId);
}
