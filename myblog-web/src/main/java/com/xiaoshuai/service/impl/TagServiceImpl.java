package com.xiaoshuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.ArticlePreviewDTO;
import com.xiaoshuai.dto.ArticlePreviewListDTO;
import com.xiaoshuai.dto.TagDTO;
import com.xiaoshuai.entity.ArticleTag;
import com.xiaoshuai.entity.Tag;
import com.xiaoshuai.mapper.ArticleMapper;
import com.xiaoshuai.mapper.ArticleTagMapper;
import com.xiaoshuai.mapper.TagMapper;
import com.xiaoshuai.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai.vo.AddOrEditTagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
   private ArticleMapper articleMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Override
    public List<TagDTO> listTagDTO() {
        List<TagDTO> tagDTOS = this.baseMapper.listTagDTO();
        return  tagDTOS;
    }

    @Override
    public ArticlePreviewListDTO listTagsByTagId(Integer tagId, Integer current) {
        //转换页码
        Page<ArticlePreviewDTO> page = new Page<>(current, 9);
        IPage<ArticlePreviewDTO> articles = articleMapper.listTagsByCondition(page, tagId);
        List<ArticlePreviewDTO> records = articles.getRecords();
        String name = null;
        return new ArticlePreviewListDTO(records,name);
    }

    @Override
    public boolean addOrEditTag(AddOrEditTagVO addOrEditTagVO) {
        if (addOrEditTagVO==null){
            throw  new RuntimeException();
        }
        Integer tagId = addOrEditTagVO.getTagId();
        Tag tag = new Tag();
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        tag.setTagName(addOrEditTagVO.getTagName());
        if(tagId!=null){
         //说明这是编辑博客 那么就需要给一个更新时间
            tag.setTagId(tagId);
            tag.setUpdateTime(date);
        }else {
            //说明是新增标签
            tag.setCreateTime(date);
        }
        boolean b = this.saveOrUpdate(tag);
        return  b;

    }

    @Override
    public boolean deleteTag(Integer tagId) {
         //先删除这个标签列表
        boolean b = this.removeById(tagId);
        //在删除关联表里面得标签
        QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_id", tagId);
        int delete = articleTagMapper.delete(wrapper);
        return  b;
    }
}
