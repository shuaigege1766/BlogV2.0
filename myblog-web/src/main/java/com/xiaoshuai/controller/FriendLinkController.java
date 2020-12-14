package com.xiaoshuai.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.entity.FriendLink;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.FriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-01
 */
@RestController
@Api(tags = "友链模块")
@RequestMapping("/friendLink")
public class FriendLinkController {
       @Autowired
     private FriendLinkService friendLinkService;



    @ApiOperation(value = "添加或者修改友链")
    @PostMapping("/addOrEditFriendLink")
    public Result addOrEditFriendLink(@RequestBody FriendLink friendLink){
        boolean b = friendLinkService.addOrEditFriendLink(friendLink);
        if(b){
            return Result.ok();
        }else {
            return  Result.error();
        }

    }

    @ApiOperation(value = "根据关键词分页查询友链")
    @GetMapping("/listLinks")
    public Result listLinks(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                            @RequestParam(value = "size",required = true,defaultValue = "100") Integer size,
                            @RequestParam(value = "keywords",required = false) String keywords){


        IPage<FriendLink> page = friendLinkService.pageLinks(current, size, keywords);
        long total = page.getTotal();
        List<FriendLink> data = page.getRecords();
        return  Result.ok().data("data", data).data("total", total);
    }
}

