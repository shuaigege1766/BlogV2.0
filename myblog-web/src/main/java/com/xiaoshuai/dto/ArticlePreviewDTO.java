package com.xiaoshuai.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-30-上午 10:17
 **/

@Data
public class ArticlePreviewDTO {
    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章缩略图
     */
    private String articleCover;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 发表时间
     */
    private Date createTime;

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
