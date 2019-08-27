package com.roc.jframeworkweb.utils.test;

import com.roc.jframework.web.utils.JWTUitls;
import io.jsonwebtoken.Claims;
import org.junit.Test;

public class JWTUtilsTest {

    @Test
    public void test_createJWT(){
        String jwt = JWTUitls.createJWT("111", "roc", 1000 * 60 * 5);
        System.out.println(jwt);

        Claims claims = JWTUitls.parseJWT(jwt);
        System.out.println(claims.getId());
        System.out.println(claims.getIssuer());
        System.out.println(claims.getExpiration());
    }
}
