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
@TableName("tb_comment")
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "//评论主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "//用户id")
    private Integer userId;

    @ApiModelProperty(value = "//文章id")
    private Integer articleId;

    @ApiModelProperty(value = "//评论内容")
    private String commentContent;

    @ApiModelProperty(value = "//评论时间")
    private Date createTime;

    @ApiModelProperty(value = "//回复用户id")
    private Integer replyId;

    @ApiModelProperty(value = "//父评论id")
    private Integer parentId;

    @ApiModelProperty(value = "//是否删除")
    private Boolean isDelete;


}
