package com.xiaoshuai.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.CategoryDTO;
import com.xiaoshuai.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-24
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryDTO> listCategories();
}
