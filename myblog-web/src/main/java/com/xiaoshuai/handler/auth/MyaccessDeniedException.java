package com.xiaoshuai.handler.auth;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-07-下午 17:02
 **/

public class MyaccessDeniedException extends AccessDeniedException {

    /**
     * Constructs an <code>AccessDeniedException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public MyaccessDeniedException(String msg) {
        super(msg);
    }
}
