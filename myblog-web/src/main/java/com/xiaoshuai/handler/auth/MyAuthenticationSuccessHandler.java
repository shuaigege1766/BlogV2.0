package com.xiaoshuai.handler.auth;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoshuai.entity.User;
import com.xiaoshuai.entity.UserLogin;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.result.ResultInfo;
import com.xiaoshuai.service.UserLoginService;
import com.xiaoshuai.service.impl.auth.UserAuth;
import com.xiaoshuai.utils.IpUtil;
import com.xiaoshuai.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-16-上午 11:11
 **/
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserLoginService loginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //验证成功来到这个处理器
        //然后获取用户信息
        UserAuth user = (UserAuth) authentication.getPrincipal();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.ok()
                .code(ResultInfo.VERIFY_SUCCESS.getCode())
                .message(ResultInfo.VERIFY_SUCCESS.getMessage()).data("user", user)));
        //更新用户登录ip地址，最新登录时间
        String ipAddress = IpUtil.getIp(request);
        String ipSource = IpUtil.getIpSource(ipAddress);
        User loginUser = user.getUser();
        UserLogin login = new UserLogin();
         login.setAvatar(loginUser.getAvatar());
         login.setIpAddress(ipAddress);
         login.setIpSources(ipSource);
         login.setNickname(loginUser.getNickname());
         login.setLoginTime(new Date());
        //查询他上次得登录时间设置为上次登录时间
        //这个时间应该设置到redis中 每次登录就存入redis作为上次登录时间 并且每次更新
        //存入表
        loginService.save(login);
    }
}
