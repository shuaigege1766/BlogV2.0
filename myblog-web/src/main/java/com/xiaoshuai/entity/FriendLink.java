package com.xiaoshuai.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_friend_link")
@ApiModel(value="FriendLink对象", description="友链")
public class FriendLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "友链主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "链接名")
    private String linkName;

    @ApiModelProperty(value = "链接头像")
    private String linkAvatar;

    @ApiModelProperty(value = "链接地址")
    private String linkAddress;

    @ApiModelProperty(value = "链接简介")
    private String linkIntro;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
