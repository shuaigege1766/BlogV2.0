package com.xiaoshuai.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.dto.CommentBackDTO;
import com.xiaoshuai.dto.CommentDTO;
import com.xiaoshuai.dto.PageDTO;
import com.xiaoshuai.dto.ReplyDTO;
import com.xiaoshuai.entity.UserLogin;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.CommentService;
import com.xiaoshuai.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@Api(tags = "评论模块")
@RequestMapping("/comment")
public class CommentController {
     @Autowired
     private CommentService commentService;


    @ApiOperation(value = "查询评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "current", value = "当前页码", required = true, dataType = "Integer")})
    @GetMapping("/comments")
    private Result listComments(@RequestParam(value = "articleId") Integer articleId, @RequestParam(value = "current")Integer current) {

        PageDTO<CommentDTO> data = commentService.listComments(articleId, current);
        return  Result.ok().data("data", data);

    }

    @ApiOperation(value = "添加评论或回复")
    @PostMapping("/comments")
    private Result saveComment(@RequestBody CommentVO commentVO) {

      commentService.saveComment(commentVO);
        
       return  Result.ok();
    }

    @ApiOperation(value = "查看回复评论")
    @GetMapping("/comments/replies")
    private Result listRepliesByCommentId(Integer commentId, Long current) {


        List<ReplyDTO> data = commentService.listRepliesByCommentId(commentId, current);

        return  Result.ok().data("data", data);
    }


    @ApiOperation(value = "分页获取用户评论列表")
    @GetMapping("/getUserCommentList")
    public Result getUserInfoList (Integer current,Integer size ,String nickname){

        List<CommentBackDTO> list = commentService.getUserReplyList(current, size, nickname);
        int total = commentService.count();
        return  Result.ok().data("total", total).data("data", list);

    }


}

