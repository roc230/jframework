package com.roc.jframework.basic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 通正则表达式查找目标内容
     * @param content 基于查找的内容
     * @param reg 正则表达式
     * @param setIndex 组下标，如果没分组默认下标为0
     * @return
     */
    public static String findByReg(String content, String reg, Integer setIndex){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()){
            return matcher.group(setIndex);
        }
        return null;
    }
}
