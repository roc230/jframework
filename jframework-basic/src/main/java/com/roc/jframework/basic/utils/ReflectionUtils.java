package com.roc.jframework.basic.utils;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionUtils {

    public static List<Field> getFields(Class clazz){
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                return ListUtils.newArrayList(fields);
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了

            }
        }

        return null;
    }
}
