package com.xiaoshuai.handler.auth;

import com.alibaba.fastjson.JSON;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.result.ResultInfo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-16-上午 11:14
 **/
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,

                                        AuthenticationException exception) throws IOException, ServletException {
         //来到登录失败处理器
        //这个返回可以封装一下 就2个不用了
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.error()
                .code(ResultInfo.LOGIN_FAILED.getCode())
                .message(ResultInfo.LOGIN_FAILED.getMessage())));
    }
}
