package com.xiaoshuai.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_article_tag")
@ApiModel(value="ArticleTag对象", description="文章标签关联类")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "//article 和 tag的关联 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "//标签id")
    private Integer tagId;

    @ApiModelProperty(value = "//文章id")
    private Integer articleId;

    public ArticleTag(Integer tagId, Integer articleId) {
        this.tagId = tagId;
        this.articleId = articleId;
    }
}
