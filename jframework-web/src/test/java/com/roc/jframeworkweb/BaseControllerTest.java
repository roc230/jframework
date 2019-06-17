package com.roc.jframeworkweb;

import com.roc.jframework.web.JframeworkWebApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JframeworkWebApplication.class})
@AutoConfigureMockMvc
public class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    protected MockHttpServletRequestBuilder get(String url){
        return MockMvcRequestBuilders.get(url);
    }

    protected MockHttpServletRequestBuilder post(String url, Object... params){
        return MockMvcRequestBuilders.post(url, params);
    }
}
