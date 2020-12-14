package com.xiaoshuai.config;

import com.xiaoshuai.handler.auth.*;
import com.xiaoshuai.service.impl.auth.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-16-上午 10:34
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService  myUserDetailsService;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Override
    public void configure(HttpSecurity http) throws Exception {
       //        什么是（cors 预检请求） 就是你要跨域请求得时候 你要预先发一个请求看对面是拦你还是放你
        //第1步：解决跨域问题。cors 预检请求放行,让Spring security 放行所有preflight request（cors 预检请求）
        http.formLogin().loginProcessingUrl("/login").permitAll();
        http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
       //放行掉这个iframe加载
        http.headers().frameOptions().disable();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
//        //禁用csrf
//        http.csrf().disable();
//                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().headers().cacheControl()
       //配置权限
                 http
               .authorizeRequests()
//                 放行注册请求
               .antMatchers(HttpMethod.POST,"/user/register").permitAll()
               .anyRequest()
               .access(("@dynamicPermission.checkPermisstion(authentication)"))
               .and().csrf().disable();
//                 自己的过滤器
         http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        //第6步：处理异常情况：认证失败和权限不足
         http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);
    }

    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return  filter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    //用户密码加密验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.userDetailsService( myUserDetailsService).passwordEncoder(passwordEncoder());
    }
    
}
