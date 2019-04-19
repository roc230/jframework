package com.roc.jframework.basic.encodedecode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEndecodeUtils {

    /**
     * 加密
     * @param txt 明文内容
     * @param charset 字符集
     * @return
     */
    public static String encode(String txt, String charset){
        try {
            return URLEncoder.encode(txt, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param encodeTxt 密文
     * @param charset 字符集
     * @return
     */
    public static String decode(String encodeTxt, String charset){
        try {
            return URLDecoder.decode(encodeTxt, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
