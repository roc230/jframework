package com.roc.jframework.basic.encodedecode;

public class HexUtils {

    /**
     * 二进制字节数组转十六进制字符串
     * @param bytes 二进制字节数组
     * @return
     */
    public static String byteToHex(byte[] bytes){
        StringBuilder sb = new StringBuilder();

        String tmp = null;

        for(int i = 0; i < bytes.length; i++){
            tmp = Integer.toHexString(bytes[i] & 0xFF).toUpperCase();
            if(tmp.length() == 1){
                sb.append("0").append(tmp);
            }else{
                sb.append(tmp);
            }
        }

        return sb.toString();
    }

    /**
     * 十六进制字符串转二进制字节数组
     * @param hex 十六进制字符串
     * @return
     */
    public static byte[] hexToBytes(String hex){
        if(hex.length() % 2 != 0){
            throw new IllegalArgumentException("参数内容不是hex字符串");
        }
        char[] chars = hex.toCharArray();
        byte[] bytes = new byte[hex.length()/2];
        for(int i = 0, j = 0, k = hex.length(); i < k; i++, j++){
            String swap = "" + chars[i++] + chars[i];
            int byteInt = Integer.parseInt(swap, 16) & 0xFF;
            bytes[j] = new Integer(byteInt).byteValue();
        }
        return bytes;
    }
}
