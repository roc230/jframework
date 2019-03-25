package com.roc.jframework.service;

public interface ILoginService {

    /**
     * 使用帐户密码登陆
     * @param accountName 帐户名称
     * @param password 帐户密码
     * @return
     */
    String login(String accountName, String password);

    /**
     * 手机号较验码方式登陆
     * @param mobile 手机号
     * @param checkCode 手机信息较验码
     * @return
     */
    String loginByMobile(String mobile, String checkCode);

    /**
     * 微信授权登陆
     * @return
     */
    String loginByWeixin();

    /**
     * 帐户名是否已存在
     * @param accountName 帐户名
     * @return
     */
    Boolean existsAccount(String accountName);

    /**
     * 退出
     * @param accountName
     * @return
     */
    String logout(String accountName);

}
