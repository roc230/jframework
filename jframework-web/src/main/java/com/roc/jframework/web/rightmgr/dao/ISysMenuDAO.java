package com.roc.jframework.web.rightmgr.dao;

import com.roc.jframework.web.bookmgr.dao.BaseRepository;
import com.roc.jframework.web.rightmgr.entity.SysMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISysMenuDAO  extends BaseRepository<SysMenu> {

    @Query("select m from SysMenu m where m.enable = true")
    List<SysMenu> getAllEnable();

    @Query("select m from SysMenu m where m.enable = true and m.id = :id")
    SysMenu getById(@Param("id") String id);

    @Query("select m from SysAccount a join a.roles r join r.permissions p join p.menu m where m.enable = true and m.type <> 'ACTION' and a.loginName = :loginName")
    List<SysMenu> getMenusByLoginName(@Param("loginName") String loginName);

    @Query("select m from SysAccount a join a.roles r join r.permissions p join p.menu m where m.enable = true and m.type = 'ACTION' and a.loginName = :loginName and m.parent.id = :menuId")
    List<SysMenu> getActionsByMenuIdAndLoginName(@Param("menuId") String menuId, @Param("loginName") String loginName);

    @Query("select m from SysMenu m join m.parent p where m.enable = true and p.id = :menuId")
    List<SysMenu> getChildren(@Param("menuId") String menuId);

    @Query("select m from SysMenu m where m.enable = true and m.parent is null")
    List<SysMenu> getRootMenus();
}
