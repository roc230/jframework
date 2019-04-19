package com.roc.jframework.basic.encodedecode;

import java.util.Base64;

public class Base64EndecodeUtils {

    public static String encode(String txt){
        return Base64.getEncoder().encodeToString(txt.getBytes());
    }

    public static String decode(String encodeTxt){
        byte[] bytes = Base64.getDecoder().decode(encodeTxt);
        return new String(bytes);
    }
}
