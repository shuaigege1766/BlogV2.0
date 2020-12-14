package com.xiaoshuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.dto.ArchiveDTO;
import com.xiaoshuai.dto.ArticleHomeDTO;
import com.xiaoshuai.dto.ListArticleDTO;
import com.xiaoshuai.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai.vo.SaveOrUpdateArticleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-24
 */
public interface ArticleService extends IService<Article> {


    int saveOrUpdateArticle(SaveOrUpdateArticleVO articleVO);

    List<ListArticleDTO> getArticleList(Integer current, Integer size, String articleTitle);


    List<ArticleHomeDTO> listArticles(Long current);

    int  deleteArticleById(Integer articleId);


    IPage<ArchiveDTO> listArchives(Integer current);


    SaveOrUpdateArticleVO  getArticleById(Integer articleId);
}
