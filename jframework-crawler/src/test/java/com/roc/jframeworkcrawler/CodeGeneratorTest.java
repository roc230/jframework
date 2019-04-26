package com.roc.jframeworkcrawler;

import com.roc.jframework.codegenerator.BuilderGenerator;
import com.roc.jframework.crawler.novelcopyright.NovelSearch;
import org.junit.Test;

public class CodeGeneratorTest {

    @Test
    public void test(){
        String code = BuilderGenerator.builder(NovelSearch.class, 8);
        System.out.println(code);
    }
}
