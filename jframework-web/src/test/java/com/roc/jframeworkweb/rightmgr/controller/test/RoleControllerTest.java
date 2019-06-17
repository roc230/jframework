package com.roc.jframeworkweb.rightmgr.controller.test;

import com.roc.jframework.basic.ext.HashMapExt;
import com.roc.jframeworkweb.BaseControllerTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

public class RoleControllerTest extends BaseControllerTest {

    @Test
    public void test_getRoles() throws Exception{
        MvcResult r = this.mockMvc.perform(get("/role/all"))
                .andReturn();
        String s = r.getResponse().getContentAsString();
        System.out.println(s);
    }

    @Test
    public void test_saveRole() throws Exception{
        Map<String,String> map = new HashMapExt<>();
        map.put("id", "2");
        map.put("name", "testrole");
        MvcResult r = this.mockMvc.perform(post("/role/save", map))
                .andReturn();
    }

}
