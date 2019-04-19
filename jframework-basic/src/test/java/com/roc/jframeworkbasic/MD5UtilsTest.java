package com.roc.jframeworkbasic;

import com.roc.jframework.basic.encodedecode.MD5Utils;
import org.junit.Test;

public class MD5UtilsTest {

    @Test
    public void test(){
        String txt = "hello world";

        String encode = MD5Utils.encode(txt, "123");

        System.out.println(encode);
        System.out.println(MD5Utils.verify(txt, "123", encode));
    }
}
