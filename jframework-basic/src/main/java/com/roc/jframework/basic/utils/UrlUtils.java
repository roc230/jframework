package com.roc.jframework.basic.utils;

import java.util.Map;

public class UrlUtils {

    /**
     * 为url添加请求参数
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String addParam(String url, String name, String value){
        if(StringUtils.hasNullOrEmpty(url, name)){
            return url;
        }
        //已带参数
        if(url.indexOf("?") > 0){
            return url + "&" + name + "=" + value;
        }
        return url +"?" + name + "=" + value;
    }

    /**
     * 为url添加多个请求参数
     * @param url
     * @param params
     * @return
     */
    public static String addParams(String url, Map<String,String> params){
        String newUrl = new String(url);
        for(Map.Entry<String,String> entry : params.entrySet()){
            newUrl = addParam(newUrl, entry.getKey(), entry.getValue());
        }
        return newUrl;
    }
}
