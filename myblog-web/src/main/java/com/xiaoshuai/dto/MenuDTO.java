package com.xiaoshuai.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-23-上午 9:35
 **/
@Data
public class MenuDTO {
    @ApiModelProperty(value = "//后台菜单id")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;
    @ApiModelProperty(value = "//用户角色")
    private String userRole;
    @ApiModelProperty(value = "//菜单列表名字")
    private String menuName;

    @ApiModelProperty(value = "//菜单的url")
    private String menuUrl;

    @ApiModelProperty(value = "//菜单的父ID")
    private Integer parentId;

    @ApiModelProperty(value = "//菜单排序")
    private Integer menuSort;

    @ApiModelProperty(value = "//描述")
    private String description;
    @ApiModelProperty(value = "//菜单图标")
    private String menuIcon;
    @ApiModelProperty(value = "//二级菜单目录")
    private List<MenuDTO> children;


}
