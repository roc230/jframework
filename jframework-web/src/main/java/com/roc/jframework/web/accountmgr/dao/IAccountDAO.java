package com.roc.jframework.web.accountmgr.dao;

import com.roc.jframework.web.accountmgr.entity.Account;
import com.roc.jframework.web.bookmgr.dao.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDAO extends BaseRepository<Account> {
}
