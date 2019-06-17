package com.roc.jframework.web.rightmgr.service;

/**
 * 授权服务
 */
public interface IAuthorizeService {

    /**
     * 把角色授予帐户
     * @param roleId 角色ID
     * @param accountId 帐户ID
     */
    void grantRoleToAccount(String roleId, String accountId);

    /**
     * 撤销指定帐户的角色
     * @param roleId 角色ID
     * @param accountId 帐户ID
     */
    void deleteRoleFromAccount(String roleId, String accountId);

}
