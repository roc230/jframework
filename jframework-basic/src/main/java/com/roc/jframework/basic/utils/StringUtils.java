package com.roc.jframework.basic.utils;

public class StringUtils {

    public static boolean isNullOrEmpty(String str){
        if(str == null){
            return true;
        }
        if(str.trim().length() < 1){
            return true;
        }
        return false;
    }
}
