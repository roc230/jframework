package com.roc.jframework.web.rightmgr.service;


public interface ILoginService {

    Boolean existsLoginName(String loginName);

    String login(String loginName, String password);
}
