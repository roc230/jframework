package com.roc.jframework.basic.utils;

import java.util.List;

public class ListUtils {

    public static <T> boolean isNullOrEmpty(List<T> list){
        if(list == null){
            return true;
        }
        return list.isEmpty();
    }
}
