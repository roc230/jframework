package com.roc.jframework.basic.encodedecode;

public class UnicodeUtils {

    public static String decode(String txt){
        StringBuffer sb = new StringBuffer();
        String[] hex = txt.split("\\\\u");

        for(int i = 1; i < hex.length; i++){
            int data = Integer.parseInt(hex[i], 16);
            sb.append((char)data);
        }

        return sb.toString();
    }

    public static String encode(String txt){
        StringBuffer sb = new StringBuffer();
        char[] source_char = txt.toCharArray();
        String encode = null;
        for(int i = 0; i < source_char.length; i++){
            encode = Integer.toHexString(source_char[i]);
            if(encode.length() <= 2){
                encode = "00" + encode;
            }
            sb.append("\\u").append(encode);
        }
        return sb.toString();
    }
}
