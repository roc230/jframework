package com.roc.jframework.web.rightmgr.dao;

import com.roc.jframework.web.bookmgr.dao.BaseRepository;
import com.roc.jframework.web.rightmgr.entity.SysPermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface ISysPermissionDAO extends BaseRepository<SysPermission> {

    List<SysPermission> findAll();

    List<SysPermission> findSysPermissionsByIdIn(List<String> ids);

    @Query("select p from SysAccount a join a.roles r join r.permissions p join fetch p.menu m where m.enable = true and a.id = :accountId")
    List<SysPermission> getByAccountId(@PathParam("accountId") String accountId);

    @Query("select p from SysRole r join r.permissions p join fetch p.menu m where m.enable = true and r.id = :roleId")
    List<SysPermission> getByRoleId(@PathParam("roleId") String roleId);

    @Query("select p from SysPermission p join p.menu m where m.enable = true and m.name in (:names)")
    List<SysPermission> getByNames(@PathParam("names") List<String> names);

}
