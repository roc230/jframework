package com.roc.jframework.basic.utils;

import java.util.ArrayList;
import java.util.LinkedList;
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

    /**
     * 相反顺序
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> reverse(List<T> list){
        List<T> l = new ArrayList<>();
        for(int i = list.size()-1; i > -1; i--){
            l.add(list.get(i));
        }
        return l;
    }

    public static <T> List<T> copy(List<T> list){
        List<T> newlist = new ArrayList<>();
        for(T o : list){
            newlist.add(o);
        }
        return newlist;
    }
}
