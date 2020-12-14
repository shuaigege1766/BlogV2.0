package com.xiaoshuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.ArchiveDTO;
import com.xiaoshuai.dto.ArticleHomeDTO;
import com.xiaoshuai.dto.ListArticleDTO;
import com.xiaoshuai.entity.Article;
import com.xiaoshuai.entity.ArticleTag;
import com.xiaoshuai.entity.Comment;
import com.xiaoshuai.mapper.ArticleMapper;
import com.xiaoshuai.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai.service.ArticleTagService;
import com.xiaoshuai.service.CommentService;
import com.xiaoshuai.service.TagService;
import com.xiaoshuai.utils.HTMLUtil;
import com.xiaoshuai.vo.SaveOrUpdateArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
     @Autowired
    private ArticleTagService articleTagService;
     @Autowired
     private  ArticleService articleService;
     @Autowired
     private CommentService commentService;

    /**
     *  更新或者新增文章
     * @param articleVO
     * @return
     */
    @Transactional
    @Override
    public int saveOrUpdateArticle(SaveOrUpdateArticleVO articleVO) {
        //如果是更新 那么articleId 不可能是0或者空记得开启事务
        Integer articleId = articleVO.getArticleId();
        Article article = new Article(articleVO);
        //更新状态
        if(       articleId !=null
                && !articleVO.getIsDraft()){
//          如果是更新就先删除原来的所有标签 然后在新增进去标签 分类在文章里面 直接更新即可
         //在关联表里面删除
            QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();
            wrapper.eq("article_id", articleId);
            articleTagService.remove(wrapper);
        }
          //开始更新
            articleService.saveOrUpdate(article);
            //还要插入这个文章所属的标签
            //获取需要插入的标签id列表
            List<Integer> tagIdList = articleVO.getTagIdList();
            ArrayList<ArticleTag> articleTags = new ArrayList<>();
            ArticleTag articleTag;
            for( Integer tagId:tagIdList){
                //新增的有个自增id  更新的话也是从里面获取id
                articleTag=new ArticleTag(tagId,article.getArticleId());
               articleTags.add(articleTag);
            }
            //批量保存标签
            boolean b1 = articleTagService.saveBatch(articleTags);
            return 1;
    }


    @Override
    public List<ListArticleDTO> getArticleList(Integer current, Integer size, String articleTitle) {

        return  this.baseMapper.getArticleList((current-1)*size,size,articleTitle);
    }

    
    @Override
    public List<ArticleHomeDTO> listArticles(Long current) {
        List<ArticleHomeDTO> articleHomeDTOS = this.baseMapper.listArticles((current - 1) * 10);
        //文章内容过滤markdown标签展示
        for (ArticleHomeDTO articleDTO : articleHomeDTOS) {
            articleDTO.setArticleContent(HTMLUtil.deleteArticleTag(articleDTO.getArticleContent()));
        }
        return articleHomeDTOS;
    }

    @Transactional
    @Override
    public int deleteArticleById(Integer articleId) {
        //先删除对应的文章
        int i = this.baseMapper.deleteById(articleId);
        //在删除在关联表中的标签
        QueryWrapper<ArticleTag> articleTagQueryWrapper = new QueryWrapper<>();
        articleTagQueryWrapper.eq("article_id", articleId);
        int delete = articleTagService.getBaseMapper().delete(articleTagQueryWrapper);
        //在删除博客的评论其实可以不用删 反正博客没了就看不见了
        //但是还是要删除的
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId);
        boolean remove = commentService.remove(wrapper);
        return  delete;
    }

    @Override
    public IPage<ArchiveDTO> listArchives(Integer current) {
        Page<ArchiveDTO> page = new Page<>(current, 10);
        IPage<ArchiveDTO> archives = this.baseMapper.listArchives(page);
        return archives;

    }

    @Override
    public SaveOrUpdateArticleVO getArticleById(Integer articleId) {
        //获取当前文章的信息
        System.out.println(articleId);
        Article article = articleService.getById(articleId);
        //在获取当前文章的标签列表id信息
        QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id",articleId);
        List<ArticleTag> articleTags = articleTagService.list(wrapper);
        ArrayList<Integer> tagsId = new ArrayList<>();
        for ( ArticleTag articleTag  : articleTags){
            tagsId.add(articleTag.getTagId());
         }
        SaveOrUpdateArticleVO articleVO = new SaveOrUpdateArticleVO(article.getArticleId(),
                article.getArticleTitle(), article.getArticleContent(), article.getArticleCover(), article.getCategoryId(), article.getIsTop(), article.getIsDraft(), tagsId);
        return articleVO;
    }

}
