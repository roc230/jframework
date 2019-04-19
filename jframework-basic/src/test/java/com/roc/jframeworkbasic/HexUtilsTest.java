package com.roc.jframeworkbasic;

import com.roc.jframework.basic.encodedecode.HexUtils;
import org.junit.Test;

public class HexUtilsTest {

    @Test
    public void test(){
        String txt = "hello world";
        String encode = HexUtils.byteToHex(txt.getBytes());
        String decode = new String(HexUtils.hexToBytes(encode));

        System.out.println(encode);
        System.out.println(decode);
    }
}
