package com.roc.jframework.codegenerator;

public class SpaceUtils {

    public static String space(int count){
        String s = "";
        for(int i = 0; i < count; i++){
            s += " ";
        }
        return s;
    }
}
