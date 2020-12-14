package com.xiaoshuai.controller;

import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.AliOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-24-上午 11:20
 **/
@RestController
@Api(tags = "阿里云对象存储ossAPI模块")
public class AliOssController {

    @Autowired
    private AliOssService aliOssService;

    @PostMapping("/uploadImage")
    @ApiOperation(value = "图片上传")
    public Result upload(MultipartFile file){
        String url = aliOssService.upload(file);
        return  Result.ok().data("url", url);
    }
}
