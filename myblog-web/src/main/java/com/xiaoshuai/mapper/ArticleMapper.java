package com.xiaoshuai.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.ArchiveDTO;
import com.xiaoshuai.dto.ArticleHomeDTO;
import com.xiaoshuai.dto.ArticlePreviewDTO;
import com.xiaoshuai.dto.ListArticleDTO;
import com.xiaoshuai.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-24
 */
public interface ArticleMapper extends BaseMapper<Article> {


     List<ListArticleDTO> getArticleList( @Param("current")Integer current,@Param("size")Integer size, @Param("articleTitle") String articleTitle);

     List<ArticleHomeDTO> listArticles(Long current);

     IPage<ArchiveDTO>  listArchives(Page<ArchiveDTO> page);

    IPage<ArticlePreviewDTO> listArticlesByCondition(Page<ArticlePreviewDTO> page,@Param(value = "categoryId") Integer categoryId);
    IPage<ArticlePreviewDTO> listTagsByCondition(Page<ArticlePreviewDTO> page,@Param(value = "tagId") Integer tagId);
}
