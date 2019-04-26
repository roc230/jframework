package com.roc.jframework.basic.utils;

import javax.activation.UnsupportedDataTypeException;
import java.util.Date;
import java.util.List;
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

    public static boolean isAnyNullOrEmpty(String... strings){
        if(strings == null){
            return true;
        }

        for(String str : strings){
            if(str == null || str.trim().length() < 1){
                return true;
            }
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

    public static Integer toInteger(String str){
        return Integer.valueOf(str);
    }

    public static Double toDouble(String str){
        return Double.valueOf(str);
    }

    public static Float toFloat(String str){
        return Float.valueOf(str);
    }

    public static <T> T convertToNumeric(String str, Class<T> classOfT) throws Exception{
        if(classOfT.equals(Integer.class)){
            return (T)Integer.valueOf(str);
        }else if(classOfT.equals(Double.class)){
            return (T)Double.valueOf(str);
        }else if(classOfT.equals(Float.class)){
            return (T)Float.valueOf(str);
        }else if(classOfT.equals(Boolean.class)){
            return (T)Boolean.valueOf(str);
        }else if(classOfT.equals(Long.class)){
            return (T)Long.valueOf(str);
        }else if(classOfT.equals(Short.class)){
            return (T)Short.valueOf(str);
        }else{
            throw new UnsupportedDataTypeException("未支持的基本数据类型");
        }
    }

    public static List<Integer> toInteger(String... strNum){
        try {
            List<Integer> list = ListUtils.newArrayList();
            for(String s : strNum){
                Integer value = convertToNumeric(s, Integer.class);
                list.add(value);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把第一个字母变成大写
     * @param str
     * @return
     */
    public static String uppercaseFirstChar(String str){
        char first = str.charAt(0);
        first = Character.toUpperCase(first);
        return first + str.substring(1);
    }
}
