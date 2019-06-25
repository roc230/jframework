package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.web.rightmgr.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * 分页查找
     * @param pageNum 第几页
     * @param pageSize 每页数据量
     * @return
     */
    Page<SysRole> getRolesByPage(Integer pageNum, Integer pageSize);

}
