package com.roc.jframework.web.rightmgr.dao;

import com.roc.jframework.web.bookmgr.dao.BaseRepository;
import com.roc.jframework.web.rightmgr.entity.SysOrganization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISysOrganizationDAO extends BaseRepository<SysOrganization> {

    @Query("select o from SysOrganization o where o.enable = true")
    List<SysOrganization> getAllEnable();

    @Query("select o from SysOrganization o join o.parent p where o.enable = true and p.id = :pId")
    List<SysOrganization> getChildren(@Param("pId") String orgId);
}
