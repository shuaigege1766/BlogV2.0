package com.xiaoshuai.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Collection;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user_login")
@ApiModel(value="UserLogin对象", description="用户登录记录")
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_login_id", type = IdType.AUTO)
    private Integer userLoginId;
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "登录的ip地址")
    private String ipAddress;

    @ApiModelProperty(value = "ip地址来源")
    private String ipSources;

    @ApiModelProperty(value = "登录类型")
    private String loginType;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "上次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "用户角色名")
    private String roleName ;



    public UserLogin(Integer userLoginId, String nickname, String ipAddress, String ipSources, String loginType, Date loginTime, Date lastLoginTime, String avatar, String roleName) {
        this.userLoginId = userLoginId;
        this.nickname = nickname;
        this.ipAddress = ipAddress;
        this.ipSources = ipSources;
        this.loginType = loginType;
        this.loginTime = loginTime;
        this.lastLoginTime = lastLoginTime;
        this.avatar = avatar;
        this.roleName = roleName;
    }
}
