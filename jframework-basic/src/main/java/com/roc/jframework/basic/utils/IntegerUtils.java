package com.roc.jframework.basic.utils;

import java.util.List;

public class IntegerUtils {

    public static boolean isInteger(String str){
        String r = StringUtils.findByReg(str, "(^\\d)+", 1);
        if(r == null){
            return true;
        }
        return false;
    }

    public static List<Integer> toInteger(String... strs){
        List<Integer> list = ListUtils.newArrayList();
        for(String s : strs){
            list.add(Integer.valueOf(s));
        }
        return list;
    }

    public static List<Integer> toInteger(List<String> strs){
        List<Integer> list = ListUtils.newArrayList();
        for(String s : strs){
            list.add(Integer.valueOf(s));
        }
        return list;
    }


}
