package com.roc.jframeworkweb.rightmgr.service.test;

import com.roc.jframework.web.JframeworkWebApplication;
import com.roc.jframework.web.rightmgr.service.IAuthorizeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JframeworkWebApplication.class})
public class AuthorizationServiceTest {

    @Autowired
    private IAuthorizeService authorizeService;

    @Test
    public void test_grantRoleToAccount(){
        this.authorizeService.grantRoleToAccount("402893816b5568ed016b556b37dc0000", "2");
    }

    @Test
    public void test_deleteRoleFromAccount(){
        this.authorizeService.deleteRoleFromAccount("402893816b5568ed016b556b37dc0000", "2");
    }
}
