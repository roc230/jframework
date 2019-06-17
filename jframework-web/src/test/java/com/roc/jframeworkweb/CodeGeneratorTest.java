package com.roc.jframeworkweb;

import com.roc.jframework.codegenerator.BuilderGenerator;
import com.roc.jframework.web.rightmgr.entity.SysRole;
import com.roc.jframework.web.rightmgr.vo.MenuVO;
import com.roc.jframework.web.webcrawler.entity.Category;
import com.roc.jframework.web.webcrawler.entity.NovelSite;
import com.roc.jframework.web.webcrawler.entity.WebNovel;
import org.junit.Test;

public class CodeGeneratorTest {

    @Test
    public void builder(){
        BuilderGenerator.builder(MenuVO.class, 8, true);
    }
}
