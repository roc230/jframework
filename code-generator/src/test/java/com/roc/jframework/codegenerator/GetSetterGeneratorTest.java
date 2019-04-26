package com.roc.jframework.codegenerator;

import com.roc.jframework.basic.utils.ReflectionUtils;
import com.roc.jframework.codegenerator.entity.Field;
import org.junit.Test;

import java.util.List;

public class GetSetterGeneratorTest {

    @Test
    public void getter() throws Exception{
        Class clazz = Field.class;
        List<java.lang.reflect.Field> list = ReflectionUtils.getFields(clazz);
        String s = GetSetterGenerator.getter(list.get(0), 4);
        System.out.println(s);
    }

    @Test
    public void setter(){
        Class clazz = Field.class;
        List<java.lang.reflect.Field> list = ReflectionUtils.getFields(clazz);
        String s = GetSetterGenerator.setter(list.get(0), 8);
        System.out.println(s);
    }
}
