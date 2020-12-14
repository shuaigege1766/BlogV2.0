package com.xiaoshuai.controller;

import com.xiaoshuai.entity.Role;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.RoleService;
import com.xiaoshuai.vo.RoleApiVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-07-上午 10:03
 **/
@RestController
@Api(tags = "角色模块")
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

      @GetMapping("/getRoleList")
     public Result getRoleList(){
          List<Role> list = roleService.list();
          return  Result.ok().data("data", list);
      }


      @ApiOperation(value = "根据角色id查询对应拥有得apiId列表")
      @GetMapping("/getAssignedApiIdByUserRoleId")
       public Result getAssignedApiIdByUserRoleId(@RequestParam(value = "actionId") Integer actionId){
          List<Integer> data = roleService.getAssignedApiIdByUserRoleId(actionId);
          return  Result.ok().data("data", data);
      }

    @ApiOperation(value = "根据当前角色id分配api列表权限")
    @PostMapping("/saveRolePermissionList")
    public Result  saveRolePermissionList(@RequestParam(value = "roleId")Integer roleId,
                                          @RequestBody List<RoleApiVO> roleApiVO){

        System.out.println(roleApiVO);
         roleService.saveRolePermissionList(roleId, roleApiVO);
         return  Result.ok();
    }
}
