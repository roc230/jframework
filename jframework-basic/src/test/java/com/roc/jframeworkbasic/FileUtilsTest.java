package com.roc.jframeworkbasic;

import com.roc.jframework.basic.utils.FileUtils;
import org.junit.Test;

public class FileUtilsTest {

    @Test
    public void test_saveAsFile(){
        FileUtils.saveAsFile("aaa \r\n \r\n bbb", "d:/a.txt", true);
    }
}
