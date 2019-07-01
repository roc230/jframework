package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.web.accountmgr.entity.Account;
import com.roc.jframework.web.rightmgr.dao.ISysAccountDAO;
import com.roc.jframework.web.rightmgr.dao.ISysPermissionDAO;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import com.roc.jframework.web.rightmgr.entity.SysPermission;
import com.roc.jframework.web.rightmgr.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountService implements IAccountService {

    @Autowired
    private ISysAccountDAO sysAccountDAO;

    @Autowired
    private ISysPermissionDAO sysPermissionDAO;

    @Override
    public Account getByLoginName(String loginName) {
        SysAccount account = this.sysAccountDAO.getByLoginName(loginName);
        if(account == null){
//            throw new UsernameNotFoundException("admin: " + loginName + " do not exist!");
        }
        List<SysPermission> permissions = this.sysPermissionDAO.findAll();
        return null;
    }
}
