package com.xiaoshuai.mapper;

import com.xiaoshuai.dto.MenuDTO;
import com.xiaoshuai.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-23
 */
public interface MenuMapper extends BaseMapper<Menu> {
       List<MenuDTO> listProduct();
     
}
