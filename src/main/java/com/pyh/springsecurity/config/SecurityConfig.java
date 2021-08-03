package com.pyh.springsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
//    授权
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll() // "/"路径允许任何人访问 // "/"路径允许任何人访问
                .antMatchers("/boss").hasRole("boss")
                .antMatchers("/guest").hasRole("guest")
                .antMatchers("/content").permitAll(); //
//        开启没有权限时自动跳转的登录页面
        http.formLogin();
        http.logout().logoutSuccessUrl("/content");
        http.rememberMe();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new SCryptPasswordEncoder())
                .withUser("springboot").password(new SCryptPasswordEncoder().encode("springsecurity")).roles("boss")
                .and()
                .withUser("guest").password(new SCryptPasswordEncoder().encode("guestpsw")).roles("guest")
                .and()
                .withUser("boss").password(new SCryptPasswordEncoder().encode("bosspsw")).roles("boss");
    }
}
