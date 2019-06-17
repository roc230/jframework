package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.web.rightmgr.entity.SysMenu;

import java.util.List;

public interface IMenuService {

    /**
     * 获取所有可用菜单
     * @return
     */
    List<SysMenu> getAllEnable();

    SysMenu getById(String id);

    /**
     * 根据登陆名获取有权限的菜单
     * @param loginName
     * @return
     */
    List<SysMenu> getMenusByLoginName(String loginName);

    /**
     * 根据菜单ID和登陆名获取有权限有操作
     * @param menuId
     * @param loginName
     * @return
     */
    List<SysMenu> getActionsByMenuAndLoginName(String menuId, String loginName);

    /**
     * 新增
     * @param menu
     * @param parentId
     * @return
     */
    SysMenu add(SysMenu menu, String parentId);

    /**
     * 修改
     * @param menu
     * @param parentId
     * @return
     */
    SysMenu update(SysMenu menu, String parentId);

    /**
     * 逻辑删除
     * @param menuId
     */
    void disable(String menuId);

    /**
     * 逻辑还原
     * @param menuId
     */
    void enable(String menuId);

    /**
     * 物理删除
     * @param menuId
     */
    void delete(String menuId);

}
