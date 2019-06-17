package com.roc.jframework.web.rightmgr.dao;

import com.roc.jframework.web.bookmgr.dao.BaseRepository;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface ISysAccountDAO extends BaseRepository<SysAccount> {

    SysAccount getByLoginName(String loginName);
}
