package com.xiaoshuai.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.ArticlePreviewDTO;
import com.xiaoshuai.dto.ArticlePreviewListDTO;
import com.xiaoshuai.dto.CategoryDTO;
import com.xiaoshuai.entity.Category;
import com.xiaoshuai.entity.Tag;
import com.xiaoshuai.mapper.ArticleMapper;
import com.xiaoshuai.mapper.CategoryMapper;
import com.xiaoshuai.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai.vo.AddOrEditCategoryVO;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
       @Autowired
  private   ArticleMapper articleMapper;

    @Override
    public List<CategoryDTO> listCategories() {
        List<CategoryDTO> categories = this.baseMapper.listCategories();
        return  categories;
    }

    @Override
    public ArticlePreviewListDTO listArticlesByCategoryId(Integer categoryId, Integer current) {
        //转换页码
        Page<ArticlePreviewDTO> page = new Page<>(current, 9);
        IPage<ArticlePreviewDTO> articles = articleMapper.listArticlesByCondition(page, categoryId);
        List<ArticlePreviewDTO> records = articles.getRecords();
        String name = null;
        return new ArticlePreviewListDTO(records,name);
    }

    @Override
    public boolean addOrEditCategory(AddOrEditCategoryVO addOrEditCategoryVO) {
        if (addOrEditCategoryVO==null){
            throw  new RuntimeException();
        }
        Integer tagId = addOrEditCategoryVO.getCategoryId();
        Category category=new Category();
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.format(date);
        category.setCategoryName(addOrEditCategoryVO.getCategoryName());
        if(tagId!=null){
            //说明这是编辑博客 那么就需要给一个更新时间
            category.setCategoryId(tagId);
            category.setUpdateTime(date);
        }else {
            //说明是新增标签
            category.setCreateTime(date);
        }
        boolean b = this.saveOrUpdate(category);
        return  b;
    }

    @Override
    public void deleteCategoryId(Integer categoryId) {
        this.removeById(categoryId);
    }
}
