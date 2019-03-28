package com.roc.jframework.basic.utils;

public class StringUtils {

    /**
     * 字符串是否为null或空串返回true,否则返回false
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str){
        if(str == null){
            return true;
        }
        if(str.trim().length() < 1){
            return true;
        }
        return false;
    }

    /**
     * 数中存在一个字符串为null或空串返回true，否则返回false
     * @param strs
     * @return
     */
    public static boolean hasNullOrEmpty(String... strs){
        for(String str : strs){
            if(isNullOrEmpty(str)){
                return true;
            }
        }
        return false;
    }
}
