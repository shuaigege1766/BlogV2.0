package com.xiaoshuai.handler.auth;

import com.alibaba.fastjson.JSON;
import com.xiaoshuai.result.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-08-上午 9:13
 **/
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**

     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(
                JSON.toJSONString(Result.error().message("请登录")));
    }
}
