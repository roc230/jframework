package com.roc.jframework.web.rightmgr.dao;

import com.roc.jframework.web.bookmgr.dao.BaseRepository;
import com.roc.jframework.web.rightmgr.entity.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISysPermissionDAO extends BaseRepository<SysPermission> {

    List<SysPermission> findAll();

    List<SysPermission> findSysPermissionsByIdIn(List<String> ids);

}
