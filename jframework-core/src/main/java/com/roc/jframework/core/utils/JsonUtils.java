package com.roc.jframework.core.utils;

import com.google.gson.GsonBuilder;

public class JsonUtils {

    public static String toString(Object obj){
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT){
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .fromJson(json, classOfT);
    }

    public static void printJson(Object obj){
        String json = toString(obj);
        System.out.println(json);
    }
}
