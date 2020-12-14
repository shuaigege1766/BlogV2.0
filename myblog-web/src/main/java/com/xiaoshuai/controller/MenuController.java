package com.xiaoshuai.controller;


import com.xiaoshuai.dto.MenuDTO;
import com.xiaoshuai.mapper.MenuMapper;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-23
 */
@RestController
@Api(tags = "菜单展示模块")
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
      //测试一下有没有变化
    @ApiOperation(value = "获取展示菜单列表")
    @GetMapping("/getMenuList")
    public Result getMenuList(){
        List<MenuDTO> menuList = menuService.listProduct();
        if(menuList==null){
            return  Result.error().message("你没有任何权限可以访问列表");
        }else{
            return  Result.ok().data("menuList", menuList);
        }
    }
}

