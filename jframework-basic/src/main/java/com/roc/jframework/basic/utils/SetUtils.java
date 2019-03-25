package com.roc.jframework.basic.utils;

import java.util.Set;

public class SetUtils {

    public static boolean isNullOrEmpty(Set set){
        if(set == null){

            int a;
            return true;
        }
        return set.isEmpty();
    }
}
