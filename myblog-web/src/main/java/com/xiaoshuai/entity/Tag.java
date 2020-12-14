package com.xiaoshuai.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_tag")
@ApiModel(value="Tag对象", description="标签")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "//标签id")
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Integer tagId;

    @ApiModelProperty(value = "//标签名称")
    private String tagName;
    @ApiModelProperty(value = "//创建时间")
    private Date createTime;
    @ApiModelProperty(value = "//更新时间")
    private  Date updateTime;

}
