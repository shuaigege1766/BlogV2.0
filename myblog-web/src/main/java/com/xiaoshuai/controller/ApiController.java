package com.xiaoshuai.controller;


import com.xiaoshuai.dto.ApiBackDTO;
import com.xiaoshuai.dto.PageDTO;
import com.xiaoshuai.entity.Api;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.ApiService;
import com.xiaoshuai.vo.ApiVO;
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
 * @since 2020-12-03
 */
@RestController
@io.swagger.annotations.Api(tags = "api模块",description = "api模块")
@RequestMapping("/api")
public class ApiController {

      @Autowired
      private ApiService apiService;
    @ApiOperation("根据swagger2的api文档读取api信息存入数据库")
     @PostMapping("/saveOrUpdateApiFromSwagger")
    public Result saveOrUpdateApiFromSwagger(@RequestBody List<Api> api){

        System.out.println(api);
        boolean b = apiService.saveOrUpdateApiFromSwagger(api);
        if(b){
            return  Result.ok();
        }
        return  Result.error();
    }

    @ApiOperation(value = "分类条件查询查询api的信息,父pid查询模块所有api")
    @GetMapping("/listApiInfoBack")
    public Result listApiInfoBack(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                                  @RequestParam(value = "size",required = true,defaultValue = "5")Integer size,
                                  @RequestParam(value = "apiId",required = false) Integer apiId){
        PageDTO<ApiBackDTO> back = apiService.listApiInfoBack(current, size, apiId);
        return  Result.ok().data("data", back);
    }


}

