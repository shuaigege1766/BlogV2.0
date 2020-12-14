package com.xiaoshuai.controller;

import com.xiaoshuai.dto.BlogHomeInfoDTO;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.BlogInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-25-下午 14:45
 **/
@RestController
@Api(tags = "博客信息模块")
@RequestMapping("/blogInfo")
public class UserInfoController {
  @Autowired
  private BlogInfoService blogInfoService;

    @ApiOperation(value = "获取博主的基本信息")
    @GetMapping("/getBlogInfo")
    public Result getBlogInfo(){
        BlogHomeInfoDTO blogInfo = blogInfoService.getBlogInfo();
        return  Result.ok().data("data", blogInfo);
    }
}
