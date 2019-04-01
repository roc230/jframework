package com.roc.jframework.core.component.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;

public interface IRequests {

    /**
     * 添加请求头参数
     * @param name
     * @param value
     * @return
     */
    IRequests header(String name, String value);

    /**
     * 添加请求参数
     * @param name
     * @param value
     * @return
     */
    IRequests param(String name, String value);

    /**
     * 设置UserAgent
     * @param userAgent
     * @return
     */
    IRequests userAgent(String userAgent);

    /**
     * 设置字符集
     * @param charset
     * @return
     */
    IRequests charset(String charset);

    IRequests accept(String accept);

    IRequests acceptEncoding(String acceptEncoding);

    IRequests acceptLanguage(String acceptLanguage);

    /**
     * 设置ContentType
     * @param contentType
     * @return
     */
    IRequests contentType(String contentType);

    /**
     * 设置Referer
     * @param referer
     * @return
     */
    IRequests referer(String referer);

    IRequests xRequestedWith(String xRequestedWith);

    /**
     * 请求请求url
     * @param url
     * @return
     */
    IRequests url(String url);

    /**
     * 发起get请求
     * @param showUrl
     * @return
     */
    CloseableHttpResponse get(Boolean showUrl);

    /**
     * 发起post请求
     * @return
     */
    CloseableHttpResponse post();
}
