package com.roc.jframeworkweb.rightmgr.service.test;

import com.roc.jframework.core.utils.JsonUtils;
import com.roc.jframework.web.JframeworkWebApplication;
import com.roc.jframework.web.rightmgr.entity.SysMenu;
import com.roc.jframework.web.rightmgr.service.IMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JframeworkWebApplication.class})
public class MenuServiceTest {

    @Autowired
    private IMenuService menuService;

    @Test
    public void test1(){
        List<SysMenu> menus = this.menuService.getActionsByMenuAndLoginName("1", "roc");
        String json = JsonUtils.toString(menus);
        System.out.println(json);
    }
}
