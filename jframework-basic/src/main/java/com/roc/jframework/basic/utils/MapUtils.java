package com.roc.jframework.basic.utils;

import java.util.Map;

public class MapUtils {

    public static boolean isNullOrEmpty(Map map){
        if(map == null){
            return true;
        }
        return (map.size() < 1 ? true : false);
    }
}
