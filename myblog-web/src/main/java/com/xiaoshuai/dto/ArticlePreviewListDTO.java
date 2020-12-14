package com.xiaoshuai.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-30-上午 10:16
 **/
@Getter
@Setter
@ToString
public class ArticlePreviewListDTO {
    /**
     * 条件对应的文章列表
     */
    private List<ArticlePreviewDTO> articlePreviewDTOList;

    /**
     * 条件名
     */
    private String name;

    public ArticlePreviewListDTO(List<ArticlePreviewDTO> articlePreviewDTOList, String name) {
        this.articlePreviewDTOList = articlePreviewDTOList;
        this.name = name;
    }

    public ArticlePreviewListDTO() {
    }
    
}
