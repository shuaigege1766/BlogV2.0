package com.xiaoshuai.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-25-上午 11:47
 **/
@Data
public class ArticleHomeDTO {
    /**
     * id
     */
    private Integer articleId;

    /**
     * 文章缩略图
     */
    private String articleCover;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 内容
     */
    private String articleContent;

    /**
     * 发表时间
     */
    private Date createTime;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 文章分类id
     */
    private Integer categoryId;

    /**
     * 文章分类名
     */
    private String categoryName;

    /**
     * 文章标签
     */
    private List<TagDTO> tagDTOList;
}
