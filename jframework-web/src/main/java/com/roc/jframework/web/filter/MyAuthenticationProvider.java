package com.roc.jframework.web.filter;

import com.roc.jframework.web.entity.ExeResult;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import com.roc.jframework.web.rightmgr.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private IAccountService accountService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MyAuthenticationProvider(IAccountService accountService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.accountService = accountService;
        this.bCryptPasswordEncoder =  bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginName = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();
        ExeResult<SysAccount> account = this.accountService.getByLoginName(loginName);
        if(account.isSuccess()){
            if(!bCryptPasswordEncoder.matches(password, account.getObj().getPassword())){
                throw new BadCredentialsException("用户名或密码错误！");
            }

            return null;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
