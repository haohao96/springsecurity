package com.pyh.springsecurity.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
//    授权
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll() // "/"路径允许任何人访问
                .antMatchers("/boss").permitAll() // "/"路径允许任何人访问
                .antMatchers("/content").hasRole("boss"); //
//        开启没有权限时自动跳转的登录页面
        http.formLogin()
                .usernameParameter("springboot")
                .passwordParameter("springsecurity");
    }
}
