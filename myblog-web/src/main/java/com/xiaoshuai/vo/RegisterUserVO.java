package com.xiaoshuai.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-01-下午 16:37
 **/
@Data
public class RegisterUserVO {

    /**
     * 用户名
     */

    @ApiModelProperty(name = "username", value = "用户名", required = true, dataType = "String")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(name = "password", value = "密码", required = true, dataType = "String")
    private String password;

    @ApiModelProperty(name = "email", value = "邮箱", required = true, dataType = "String")
    private  String email;
    /**
     * 验证码
     */
    @ApiModelProperty(name = "code", value = "邮箱验证码", required = true, dataType = "String")
    private String code;


}
