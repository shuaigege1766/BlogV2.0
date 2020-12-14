package com.xiaoshuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoshuai.dto.BlogHomeInfoDTO;
import com.xiaoshuai.entity.Article;
import com.xiaoshuai.entity.User;
import com.xiaoshuai.mapper.ArticleMapper;
import com.xiaoshuai.mapper.CategoryMapper;
import com.xiaoshuai.mapper.TagMapper;
import com.xiaoshuai.mapper.UserMapper;
import com.xiaoshuai.service.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-25-下午 14:34
 **/
@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    private UserMapper userMapper;
     @Autowired
     private ArticleMapper articleMapper;
     @Autowired
     private CategoryMapper categoryMapper;
     @Autowired
     private TagMapper tagMapper;


    @Override
    public BlogHomeInfoDTO getBlogInfo() {
         //昵称头像简介 公告 文章数量  分类数量 标签数量  公告 访问量
         //公告先写死 访问量后面在弄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("nickname","avatar","intro").eq("username", "admin");
        User user = userMapper.selectOne(queryWrapper);
        //获取文章数量
        Integer articleCount= articleMapper.selectCount(null);
        //分类数量
        Integer categoryCount = categoryMapper.selectCount(null);
        //标签数量
        Integer tagCount = tagMapper.selectCount(null);
        //公告
        String notice="来了一个小滑稽,点个赞在走吧";
        BlogHomeInfoDTO blogHomeInfoDTO
                = new BlogHomeInfoDTO(user.getNickname(),
                user.getAvatar(),
                user.getIntro(),
                articleCount,
                categoryCount,
                tagCount,
                notice,
                null);
        return blogHomeInfoDTO;
    }
}
