package com.roc.jframework.basic.encodedecode;

import org.springframework.util.DigestUtils;

public class MD5Utils {

    /**
     * 加密
     * @param txt 明文
     * @param key 密钥(不需要加密钥则为空字符串即可)
     * @return
     */
    public static String encode(String txt, String key){
        return DigestUtils.md5DigestAsHex((txt + key).getBytes());
    }


    /**
     * 校验
     * @param txt 明文
     * @param key 密钥
     * @param encodeTxt 密文
     * @return
     */
    public static boolean verify(String txt, String key, String encodeTxt){
        String encode = encode(txt, key);
        if(encode.equals(encodeTxt)){
            return true;
        }
        return false;
    }


}
