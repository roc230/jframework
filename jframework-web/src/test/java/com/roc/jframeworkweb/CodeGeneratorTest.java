package com.roc.jframeworkweb;

import com.roc.jframework.codegenerator.BuilderGenerator;
import com.roc.jframework.web.webcrawler.entity.Category;
import com.roc.jframework.web.webcrawler.entity.NovelSite;
import com.roc.jframework.web.webcrawler.entity.WebNovel;
import org.junit.Test;

public class CodeGeneratorTest {

    @Test
    public void builder(){
        BuilderGenerator.builder(NovelSite.class, 8, true);
    }
}
