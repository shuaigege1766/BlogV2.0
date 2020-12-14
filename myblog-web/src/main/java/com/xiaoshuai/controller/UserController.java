package com.xiaoshuai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.UserListPageDTO;
import com.xiaoshuai.entity.User;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.result.ResultInfo;
import com.xiaoshuai.service.AliOssService;
import com.xiaoshuai.service.UserService;
import com.xiaoshuai.vo.RegisterUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    
    @ApiOperation(value = "根据用户角色和昵称分页查询用户列表")
    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public Result getUserList(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                              @RequestParam(value = "size",required = true,defaultValue = "5") Integer size,
                              @RequestParam(value = "roleName",required = false)String roleName,
                              @RequestParam(value = "nickname",required = false) String nickname
                              ){
        Page<UserListPageDTO> page = new Page<>(current,size);
        IPage<UserListPageDTO> userListPage = userService.getUserListPage(page, roleName, nickname);
        long total = userListPage.getTotal();
        List<UserListPageDTO> data = userListPage.getRecords();
        if(total>0){
            return  Result.ok().data("data", data).data("total", total);
        } else {
            return  Result.error().code(ResultInfo.NO_DATA_FOUND.getCode())
                    .message(ResultInfo.NO_DATA_FOUND.getMessage());
        }
    }

    @ApiOperation(value = "发送邮箱验证码")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String")
    @GetMapping("/sendEmailCode")
    private Result sendCode(String email) {

       userService.sendCode(email);
      return  Result.ok().message("验证码发送成功,请注意查收");
    }


    @ApiOperation(value = "注册用户")
    @PostMapping("/registerUser")
    public Result registerUser(@RequestBody RegisterUserVO registerUserVO){

        boolean b = userService.registerUser(registerUserVO);
        return  Result.ok().message("注册成功,请前往登录");

    }


     @ApiOperation(value = "/分页单表查询用户列表")
     @GetMapping("/getUserListSignal")
     public Result  getUserListSignal(Integer current,Integer size,String nickname){
         QueryWrapper<User> queryWrapper=null;
         if(nickname!=null && nickname!=""){
             queryWrapper   = new QueryWrapper<>();
             queryWrapper.like("nickname", nickname);
         }
         IPage page = userService.page(new Page<>(current, size), queryWrapper);
         long total = page.getTotal();
         List data = page.getRecords();
         return  Result.ok().data("total", total).data("data", data);

     }
}

