package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.web.entity.ExeResult;
import com.roc.jframework.web.rightmgr.entity.SysAccount;

public interface IAccountService {

    ExeResult<SysAccount> getByLoginName(String loginName);
}
