package com.xiaoshuai.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



/**
 * 留言
 * @author 11921
 */
@Data
@ApiModel(description = "留言")
public class MessageVO {

    /**
     * 昵称
     */

    @ApiModelProperty(name = "nickname", value = "昵称", required = true, dataType = "String")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(name = "avatar", value = "头像", required = true, dataType = "String")
    private String avatar;

    /**
     * 留言内容
     */
    @ApiModelProperty(name = "messageContent", value = "留言内容", required = true, dataType = "String")
    private String messageContent;

    /**
     * 弹幕速度
     */
    @ApiModelProperty(name = "time", value = "弹幕速度", required = true, dataType = "Integer")
    private Integer time;
}
