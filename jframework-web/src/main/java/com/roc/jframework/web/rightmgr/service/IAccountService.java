package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.web.accountmgr.entity.Account;

public interface IAccountService {

    Account getByLoginName(String loginName);
}
