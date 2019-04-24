package com.roc.jframework.web.customermgr.service;

import com.roc.jframework.web.customermgr.entity.Customer;

public interface ICustomerService {

    /**
     * 登陆名是否可以注册
     * @param loginName 登陆名
     * @return
     */
    boolean checkLoginName(String loginName);

    /**
     * 客户注册
     * @param loginName 登陆名
     * @param password 密码
     * @param password2 重新输入密码
     * @param checkCode 校验码
     */
    void register(String loginName, String password, String password2, String checkCode);

    /**
     * 客户登陆
     * @param loginName 登陆名
     * @param password 密码
     * @return
     */
    Customer login(String loginName, String password);

    /**
     * 充值
     */
    void recharge();

    /**
     * 支付金币
     */
    void payCoin();
}
