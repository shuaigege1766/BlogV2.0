package com.xiaoshuai.handler.auth;

import org.springframework.security.core.AuthenticationException;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-16-上午 11:40
 **/
public class MyAuthenticationException extends AuthenticationException {
    public MyAuthenticationException(String msg) {
        super(msg);
    }
}
