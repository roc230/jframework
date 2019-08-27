package com.jframework.sso.jframeworksso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/", "/login**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                ;
    }
}
