package com.roc.jframework.core.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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

    /**
     *
     * @param json
     * @param typeOfT 写法：new TypeToken<List<T>>(){}.getType()
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Type typeOfT){
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()
                .fromJson(json, typeOfT);
    }

    public static void printJson(Object obj){
        String json = toString(obj);
        System.out.println(json);
    }
}
