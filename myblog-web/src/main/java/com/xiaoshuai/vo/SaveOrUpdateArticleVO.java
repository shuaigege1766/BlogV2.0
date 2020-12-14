package com.xiaoshuai.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-24-下午 15:41
 **/

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SaveOrUpdateArticleVO {
    
    @ApiModelProperty(value = "//文章主键id")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    @ApiModelProperty(value = "//文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "//文章内容")
    private String articleContent;

    @ApiModelProperty(value = "//文章封面")
    private String articleCover;

    @ApiModelProperty(value = "//分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "//是否置顶 0：不置顶 1：置顶")
    private Boolean isTop;

    @ApiModelProperty(value = "//是否草稿 0：不是草稿 1:草稿")
    private Boolean isDraft;

    @ApiModelProperty(value = "//创建时间")
    private Date createTime;

    @ApiModelProperty(value = "//更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "标签列表ID")
    private List<Integer> tagIdList;

    public SaveOrUpdateArticleVO(Integer articleId,
                                 String articleTitle,
                                 String articleContent,
                                 String articleCover,
                                 Integer categoryId,
                                 Boolean isTop,
                                 Boolean isDraft,
                                 List<Integer> tagIdList) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleCover = articleCover;
        this.categoryId = categoryId;
        this.isTop = isTop;
        this.isDraft = isDraft;
        this.tagIdList = tagIdList;
    }
}
