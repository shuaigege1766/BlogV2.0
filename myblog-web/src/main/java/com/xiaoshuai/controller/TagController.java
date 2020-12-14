package com.xiaoshuai.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.ArticlePreviewListDTO;
import com.xiaoshuai.dto.TagDTO;
import com.xiaoshuai.entity.Tag;
import com.xiaoshuai.handler.BusinessException;
import com.xiaoshuai.mapper.TagMapper;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.TagService;
import com.xiaoshuai.vo.AddOrEditTagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2020-11-24
 */
@RestController
@RequestMapping("/tag")
@Api(tags = "标签模块")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private TagMapper tagMapper;

    @ApiOperation(value = "后台获取所有的标签")
    @GetMapping("/getTagList")
    public Result getTagList(){
        List<Tag> tags = tagService.list();
        return  Result.ok().data("data", tags);
    }


    @ApiOperation(value = "后台分页条件查询标签列表")
    @GetMapping("/listTags")
    public Result listTags(@RequestParam(value = "current",required = true,defaultValue = "1") Integer current,
                           @RequestParam(value = "size",required = true,defaultValue = "5")Integer size,
                           @RequestParam(value = "tagName",required = false) String tagName){

        Page<Tag> page = new Page<>(current, size);
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        if(tagName!=null && tagName!=""){
            wrapper.like("tag_name", tagName);
        }
        Page<Tag> tagPage = tagService.page(page, wrapper);
        long total = tagPage.getTotal();
        List<Tag> data = tagPage.getRecords();
         if(total>0){
             return  Result.ok().data("data", data).data("total", total);
         }else {
             return  Result.ok().message("没有相关数据");
         }
    }

    @ApiOperation(value = "博客查看标签列表")
    @GetMapping("/tags")
    public Result tags(){
         //需要获取标签id，名称，和总共数量
        List<TagDTO> data = tagService.listTagDTO();
        int count = tagService.count();
        return  Result.ok().data("data", data).data("count", count);
    }


    @ApiOperation(value = "查看标签下对应的文章")
    @GetMapping("/{tagId}")
    public Result listArticlesByTagId(@PathVariable(value = "tagId") Integer tagId,
                                           Integer current){
        ArticlePreviewListDTO data = tagService.listTagsByTagId(tagId, current);
        return  Result.ok().data("data" , data);
    }

    @ApiOperation(value = "新增或者编辑标签")
    @PostMapping("/addOrEditTag")
    public Result addOrEditTag(@RequestBody AddOrEditTagVO addOrEditTagVO){
        boolean b = tagService.addOrEditTag(addOrEditTagVO);
        if(b){
            return Result.ok();
        } else {
            throw  new BusinessException();
        }
    }
    
    @ApiOperation(value = "根据id删除标签")
    @DeleteMapping("/deleteTag")
    public Result   deleteTag( Integer tagId){
        boolean b = tagService.deleteTag(tagId);
        return  Result.ok();
    }



}

