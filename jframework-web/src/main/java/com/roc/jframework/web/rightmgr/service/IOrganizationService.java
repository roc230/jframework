package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.web.rightmgr.entity.SysOrganization;

import java.util.List;

/**
 * 组织机构操作服务
 */
public interface IOrganizationService {

    /**
     * 新增
     * @param org
     * @param parentId
     * @param operatorId
     * @return
     */
    SysOrganization add(SysOrganization org, String parentId, String operatorId);

    /**
     * 修改
     * @param org
     * @param parentId
     * @param opertorId
     * @return
     */
    SysOrganization update(SysOrganization org, String parentId, String opertorId);

    /**
     * 获取所有
     * @return
     */
    List<SysOrganization> getAllEnable();

    /**
     * 获取子集
     * @param orgId
     * @return
     */
    List<SysOrganization> getChildren(String orgId);

    /**
     * 根据ID获取一个
     * @param id
     * @return
     */
    SysOrganization get(String id);

    /**
     * 逻辑删除
     * @param id
     */
    void disable(String id);

    /**
     * 物理删除
     * @param id
     */
    void delete(String id);


}
