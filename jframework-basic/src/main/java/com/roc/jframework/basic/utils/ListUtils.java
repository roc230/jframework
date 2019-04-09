package com.roc.jframework.basic.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T> boolean isNullOrEmpty(List<T> list){
        if(list == null){
            return true;
        }
        return list.isEmpty();
    }

    public static <T>  List<T> newArrayList(){
        return new ArrayList<>();
    }

    public static <T> List<T> newArrayList(T[] array){
        List<T> list = newArrayList();
        for(T o : array){
            list.add(o);
        }
        return list;
    }
}
