package com.roc.jframework.web.accountmgr.service.impl;

import com.roc.jframework.web.accountmgr.dao.IAccountDAO;
import com.roc.jframework.web.accountmgr.service.IAccountSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountService implements IAccountSerivce {

    @Resource
    private IAccountDAO accountDAO;
}
