package com.xiaoshuai.service;

import com.xiaoshuai.dto.MenuDTO;
import com.xiaoshuai.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-23
 */
public interface MenuService extends IService<Menu> {
    List<MenuDTO> listProduct();
}
