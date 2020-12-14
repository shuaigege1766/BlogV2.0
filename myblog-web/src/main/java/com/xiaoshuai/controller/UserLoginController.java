package com.xiaoshuai.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.entity.UserLogin;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-03-上午 10:03
 **/
@RestController
@Api(tags = "用户登录信息模块")
@RequestMapping("/userLogin")
public class UserLoginController {
      @Autowired
      private UserLoginService userLoginService;


      @ApiOperation(value = "分页获取用户登录信息列表")
      @GetMapping("/getUserInfoList")
      public Result getUserInfoList (Integer current,Integer size ,String nickname){
            IPage<UserLogin> page = userLoginService.getUserInfoList(current, size, nickname);
            long total = page.getTotal();
            List<UserLogin> data = page.getRecords();
            return  Result.ok().data("total", total).data("data", data);
      }
    
}
