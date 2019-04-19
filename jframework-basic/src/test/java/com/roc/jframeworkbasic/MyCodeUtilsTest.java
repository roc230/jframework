package com.roc.jframeworkbasic;

import com.roc.jframework.basic.encodedecode.MyCodeUtils;
import org.junit.Test;

public class MyCodeUtilsTest {

    @Test
    public void test() throws Exception{
        String txt = "我爱北京天安门，天安门上太阳升";

        String encode = MyCodeUtils.encode(txt, "123");
        System.out.println(encode);

        String decode = MyCodeUtils.decode(encode, "123");
        System.out.println(decode);
    }
}
