package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.web.entity.ExeResult;
import com.roc.jframework.web.entity.ResultCode;
import com.roc.jframework.web.rightmgr.dao.ISysAccountDAO;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import com.roc.jframework.web.rightmgr.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private ISysAccountDAO sysAccountDAO;

    @Override
    public ExeResult<SysAccount> getByLoginName(String loginName) {
        SysAccount account = this.sysAccountDAO.getByLoginName(loginName);
        if(account == null){
            return ExeResult.of(ResultCode.NO_QUERY_RESULT, null);
        }
        return ExeResult.of(ResultCode.SUCCESS, null, account);
    }
}
