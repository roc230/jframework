package com.roc.jframework.basic.encodedecode;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * 简单的自定义解密加密算法
 */
public class MyCodeUtils {
    /**
     * 加密
     *
     * @param source 未加密的字符串
     * @param passWord 密码
     * @return
     */
    public static String encode(String source, String passWord) {
        byte[] arr1 = source.getBytes();
        byte[] arr2 = passWord.getBytes();
        //先进行异或运算
        //在此前后进行其他个性化算法
        byte[] bytes = xorByteArray(arr1, arr2);
        //再进行Base64编码
        String encodeStr = new BASE64Encoder().encode(bytes);
        return encodeStr;
    }

    /**
     * 解密
     *
     * @param encodeStr 已经加密过的字符串
     * @param passWord 密码
     * @return
     */
    public static String decode(String encodeStr, String passWord) throws IOException {
        byte[] arr2 = passWord.getBytes();
        //先进行Base64解码
        byte[] arr1 = new BASE64Decoder().decodeBuffer(encodeStr);
        //再进行异或运算
        byte[] bytes = xorByteArray(arr1, arr2);
        //再进行Base64编码
        String decodeStr = new String(bytes, "utf-8");
        //在此前后进行其他个性化算法
        return decodeStr;
    }

    /**
     * 对两个字节数组进行抑或运算
     *
     * @param arr1 基本数组
     * @param arr2 运算数组
     * @return
     */
    protected static byte[] xorByteArray(byte[] arr1, byte[] arr2) {
        int len2 = arr2.length;
        byte[] arr3 = new byte[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr3[i] = (byte) (arr1[i] ^ arr2[i % len2]);
        }
        return arr3;
    }
}