package com.xiaoshuai.utils;

import com.alibaba.fastjson.JSON;
import com.xiaoshuai.entity.User;
import com.xiaoshuai.service.impl.auth.UserAuth;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-01-下午 17:11
 **/
public class UserUtil {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            UserAuth userAuth=   (UserAuth)authentication.getPrincipal();
            return  userAuth.getUser();
        }
        
        return  null;
    }

    public UserUtil(){}
}
