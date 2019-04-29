package com.roc.jframeworkweb.webcrawler.service;

import com.roc.jframework.web.JframeworkWebApplication;
import com.roc.jframework.web.webcrawler.entity.Category;
import com.roc.jframework.web.webcrawler.entity.Status;
import com.roc.jframework.web.webcrawler.entity.WebNovel;
import com.roc.jframework.web.webcrawler.service.ICategoryService;
import com.roc.jframework.web.webcrawler.service.IWebNovelService;
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
public class CategoryServiceTest {

    @Autowired
    private ICategoryService categoryService;

    private static Category category;

    @BeforeClass
    public static void before(){
       category = new Category.Builder()
               .name("言情")
               .recordTime(new Date())
               .enable(true)
               .build();
    }

    @Test
    public void test_save(){
        Category c = this.categoryService.save(category);
        Category c2 = this.categoryService.get(c.getId());
        System.out.println(c2.getId());
    }
}
