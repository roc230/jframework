package com.roc.jframeworkcrawler;

import com.roc.jframework.codegenerator.BuilderGenerator;
import com.roc.jframework.crawler.novelcopyright.NovelSearch;
import com.roc.jframework.crawler.novelweb.NovelInfo;
import org.junit.Test;

public class CodeGeneratorTest {

    @Test
    public void test(){
        String code = BuilderGenerator.builder(NovelInfo.class, 8, true);
        System.out.println(code);
    }
}
