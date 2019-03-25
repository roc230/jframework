package com.roc.jframework.service.impl;

import com.roc.jframework.dao.IAccountDAO;
import com.roc.jframework.service.ILoginService;

public class LoginService implements ILoginService {

    private IAccountDAO accountDAO;

    @Override
    public String login(String accountName, String password) {
        return null;
    }

    @Override
    public String loginByMobile(String mobile, String checkCode) {
        return null;
    }

    @Override
    public String loginByWeixin() {
        return null;
    }

    @Override
    public Boolean existsAccount(String accountName) {
        return null;
    }

    @Override
    public String logout(String accountName) {
        return null;
    }
}
