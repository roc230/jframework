package com.roc.jframework.web.rightmgr.service;


import com.roc.jframework.web.entity.ExeResult;

public interface ILoginService {

    ExeResult<Boolean> existsLoginName(String loginName);

    /**
     * 如果登陆成功返回jwt token
     * @param loginName
     * @param password
     * @return
     */
    ExeResult<String> login(String loginName, String password);
}
