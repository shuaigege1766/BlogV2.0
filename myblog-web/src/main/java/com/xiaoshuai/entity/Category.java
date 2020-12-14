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
@TableName("tb_category")
@ApiModel(value="Category对象", description="分类")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "//分类id")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty(value = "//分类名称")
    private String categoryName;

    @ApiModelProperty(value = "//创建时间")
    private Date createTime;
    @ApiModelProperty(value = "//更新时间")
    private  Date updateTime;

}
