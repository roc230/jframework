package com.roc.jframework.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import com.roc.jframework.web.utils.JWTUitls;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解释用户凭证
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            String loginName = request.getParameter("loginName");
            String password = request.getParameter("password");

            Authentication authentication = new UsernamePasswordAuthenticationToken(loginName,
                    password,
                    new ArrayList<>());

            return this.authenticationManager.authenticate(authentication);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 认证成功， 生成token返回给客户端
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SysAccount account = (SysAccount) authResult.getPrincipal();
        //7 days token
        String token = JWTUitls.createJWT(account.getId(), account.getLoginName(), 1000 * 60 *60 *24 *7);
        response.addHeader("Authorization", "Bearer" + token);
    }
}
