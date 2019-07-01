package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.rightmgr.dao.ISysMenuDAO;
import com.roc.jframework.web.rightmgr.entity.SysMenu;
import com.roc.jframework.web.rightmgr.entity.SysPermission;
import com.roc.jframework.web.rightmgr.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MenuService implements IMenuService {

    @Autowired
    private ISysMenuDAO sysMenuDAO;

    @Transactional(readOnly = true)
    @Override
    public List<SysMenu> getAllEnable() {
        return this.sysMenuDAO.getAllEnable();
    }

    @Transactional(readOnly = true)
    @Override
    public List<SysMenu> getMenusByLoginName(String loginName) {
        return this.sysMenuDAO.getMenusByLoginName(loginName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<SysMenu> getActionsByMenuAndLoginName(String menuId, String loginName) {
        return this.sysMenuDAO.getActionsByMenuIdAndLoginName(menuId, loginName);
    }

    @Transactional(readOnly = true)
    @Override
    public SysMenu getById(String id) {
        return this.sysMenuDAO.getById(id);
    }

    @Override
    public SysMenu add(SysMenu menu, String parentId) {
        if(StringUtils.isNullOrEmpty(menu.getId())){
            if(StringUtils.isNullOrEmpty(parentId)){
                menu.setParent(null);
            }else{
                SysMenu parent = this.sysMenuDAO.getById(parentId);
                menu.setParent(parent);
            }
            SysPermission p = new SysPermission();
            p.setMenu(menu);
            menu.setPermission(p);
            return this.sysMenuDAO.save(menu);
        }else{
            throw new RuntimeException("本方法只支付新增，请所有更新方法");
        }
    }

    @Override
    public SysMenu update(SysMenu menu, String parentId) {
        if(menu != null && !StringUtils.isNullOrEmpty(menu.getId())){
            SysMenu old = this.sysMenuDAO.getById(menu.getId());
            if(StringUtils.isNullOrEmpty(parentId)){
                old.setParent(null);
            }else{
                SysMenu parent = this.sysMenuDAO.getById(parentId);
                old.setParent(parent);
            }
            old.setDescription(menu.getDescription());
            old.setEnable(menu.getEnable());
            old.setIcon(menu.getIcon());
            old.setName(menu.getName());
            old.setType(menu.getType());
            old.setUrl(menu.getUrl());
            return this.sysMenuDAO.save(old);
        }else{
            throw new RuntimeException("本方法只支持更新，请所有新增方法");
        }
    }

    @Override
    public void disable(String menuId) {
        SysMenu menu = this.sysMenuDAO.getById(menuId);
        menu.setEnable(false);
        this.sysMenuDAO.save(menu);
    }

    @Override
    public void enable(String menuId) {
        SysMenu menu = this.sysMenuDAO.getById(menuId);
        menu.setEnable(true);
        this.sysMenuDAO.save(menu);
    }

    @Override
    public void delete(String menuId) {
        this.sysMenuDAO.deleteById(menuId);
    }

    @Override
    public List<SysMenu> getChildren(String menuId) {
        if(StringUtils.isNullOrEmpty(menuId)){
            menuId = null;
        }
        return this.sysMenuDAO.getChildren(menuId);
    }

    @Override
    public List<SysMenu> getRootMenus() {
        return this.sysMenuDAO.getRootMenus();
    }
}
