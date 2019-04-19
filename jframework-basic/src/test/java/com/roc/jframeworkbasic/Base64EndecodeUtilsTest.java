package com.roc.jframeworkbasic;

import com.roc.jframework.basic.encodedecode.Base64EndecodeUtils;
import org.junit.Test;

public class Base64EndecodeUtilsTest {

    @Test
    public void test(){
        String txt = "hello world";
        String encode = Base64EndecodeUtils.encode(txt);
        String decode = Base64EndecodeUtils.decode(encode);

        System.out.println(encode);
        System.out.println(decode);
    }
}
