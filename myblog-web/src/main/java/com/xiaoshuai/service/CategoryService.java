package com.xiaoshuai.service;

import com.xiaoshuai.dto.ArticlePreviewListDTO;
import com.xiaoshuai.dto.CategoryDTO;
import com.xiaoshuai.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai.vo.AddOrEditCategoryVO;
import com.xiaoshuai.vo.AddOrEditTagVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-24
 */
public interface CategoryService extends IService<Category> {
    List<CategoryDTO> listCategories();

    ArticlePreviewListDTO listArticlesByCategoryId(Integer categoryId,Integer current);

    boolean  addOrEditCategory(AddOrEditCategoryVO addOrEditCategoryVO);


    void deleteCategoryId(Integer categoryId) ;
}
