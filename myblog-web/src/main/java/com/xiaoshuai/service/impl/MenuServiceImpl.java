package com.xiaoshuai.service.impl;

import com.xiaoshuai.dto.MenuDTO;
import com.xiaoshuai.entity.Menu;
import com.xiaoshuai.mapper.MenuMapper;
import com.xiaoshuai.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<MenuDTO> listProduct() {
        List<MenuDTO> menuDTOS = this.baseMapper.listProduct();
        if(menuDTOS!=null){
            return menuDTOS;
        }else {
            return  null;
        }

    }
}
