package com.roc.jframeworkbasic;

import com.roc.jframework.basic.encodedecode.UrlEndecodeUtils;
import org.junit.Test;

public class UrlEncodeDecodeUtilsTest {

    @Test
    public void test(){
        String txt = "薄少，求你行行好";
        String encode = UrlEndecodeUtils.encode(txt, "utf-8");
        System.out.println(encode);

        String decode = UrlEndecodeUtils.decode(encode, "utf-8");

        System.out.println(decode);
    }
}
