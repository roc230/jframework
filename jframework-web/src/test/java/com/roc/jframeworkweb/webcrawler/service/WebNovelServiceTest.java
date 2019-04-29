package com.roc.jframeworkweb.webcrawler.service;

import com.roc.jframework.web.JframeworkWebApplication;
import com.roc.jframework.web.webcrawler.entity.Category;
import com.roc.jframework.web.webcrawler.entity.Status;
import com.roc.jframework.web.webcrawler.entity.WebNovel;
import com.roc.jframework.web.webcrawler.service.ICategoryService;
import com.roc.jframework.web.webcrawler.service.IWebNovelService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JframeworkWebApplication.class})
public class WebNovelServiceTest {

    @Autowired
    private IWebNovelService webNovelService;
    @Autowired
    private ICategoryService categoryService;


    @Before
    public void before(){

    }

    @Test
    public void test_save(){
        WebNovel novel = novel = new WebNovel.Builder()
                .alias("xxxx")
                .author("刘比")
                .brief("xxxxxxxxxxxxxxxxxxxxxx")
                .clickCount(1000)
                .coverImg("http://xxx/xxx00")
                .name("书名")
                .owner("wenxue")
                .recordTime(new Date())
                .status(Status.ON_PROGRESS)
                .url("http://dfdfdf/dfdf")
                .words(4000)
                .build();
        this.webNovelService.save(novel, new String[]{"1"}, "1");
    }
}
