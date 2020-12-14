package com.xiaoshuai.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-25-上午 9:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListArticleDTO {

    @ApiModelProperty(value = "//文章主键id")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty(value = "//文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "//分类类名")
    private String categoryName;

    @ApiModelProperty(value = "//所有的标签名")
    private List<String> tagsName;

    @ApiModelProperty(value = "//创建时间")
    private Date createTime;

    @ApiModelProperty(value = "//更新时间")
    private Date updateTime;
    
    @ApiModelProperty(value = "//是否置顶 0：不置顶 1：置顶")
    private Boolean isTop;
    

}
