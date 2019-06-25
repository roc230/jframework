package com.roc.jframework.web.rightmgr.dao;

import com.roc.jframework.web.bookmgr.dao.BaseRepository;
import com.roc.jframework.web.rightmgr.entity.SysRole;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISysRoleDAO extends BaseRepository<SysRole> {

    @Modifying
    @Query("delete from SysRole where id = :roleId and enable = true")
    void disable(@Param("roleId") String roleId);


//    @Query("select r from SysAccount a join a.roles r where r.enable = true")
//    List<SysRole> getByAccountId(String accountId);
}
