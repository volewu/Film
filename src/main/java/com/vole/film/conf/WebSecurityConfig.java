package com.vole.film.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 编写者： vole
 * Time： 2018/4/26.11:40
 * 内容：spring security 配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置用户认证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("gakki")
                .password("123")
                .roles("ADMIN");
    }

    /**
     * 请求授权
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().headers().disable()//关闭跨站、跨域、请求头
                .authorizeRequests()
                .antMatchers("/", "/static/**", "/film/**", "/webSite/**", "/webSiteInfo/**", "/aboutMe").permitAll() // 配置不需要身份认证的请求地址
                .anyRequest().authenticated() // 其他所有访问路径需要身份认证
                .and()
                .formLogin()
                .loginPage("/login") // 指定登录请求地址
                .defaultSuccessUrl("/admin") // 登录成功后的默认跳转页面
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
    }

}
