package com.xiaoshuai.controller;

import com.xiaoshuai.dto.AboutDTO;
import com.xiaoshuai.mapper.AboutMapper;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.utils.HTMLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTML;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-01-上午 9:58
 **/
@RestController
@Api(tags = "关于模块")
public class AboutController {


    @Autowired
     private AboutMapper aboutMapper;



    @ApiOperation("/获取关于我的信息")
    @GetMapping("/getAbout")
    public Result getAbout(){
        AboutDTO about = aboutMapper.getAbout();
        return  Result.ok().data("data", about);
    }

    @ApiOperation(value = "更新关于我")
    @PutMapping("/updateAbout")
     public  Result    updateAbout(String aboutContent){
        int i = aboutMapper.updateAbout(aboutContent);
        return Result.ok();
    }
    
}
