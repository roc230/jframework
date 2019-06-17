package com.roc.jframework.web.rightmgr;

import com.roc.jframework.web.rightmgr.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor securityInterceptor;

    @Bean
    AccountService accountService(){
        return new AccountService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(new String[]{"plugins/**", "/role/**"}).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .permitAll() //登陆页面用户任意访问
//                .and()
//                .logout().permitAll()
//                .and()
//                .headers()
//                .frameOptions().sameOrigin(); //注销行为任意访问
//        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("plugins/**", "/login.html")
                .and().ignoring().antMatchers("/role/**")
        .and().ignoring().antMatchers("/menu/**");
    }
}
