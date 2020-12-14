package com.xiaoshuai.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-23-下午 14:00
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserListPageDTO {
    @ApiModelProperty(value = "用户表主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户角色")
    private String description;


    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;



    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @ApiModelProperty(value = "0：不禁言  1：禁言")
    private Boolean isSilence;

  

}
