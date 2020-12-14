package com.xiaoshuai.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.entity.Message;
import com.xiaoshuai.result.Result;
import com.xiaoshuai.service.MessageService;
import com.xiaoshuai.vo.MessageVO;
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
 * @since 2020-12-02
 */
@RestController
@Api(tags = "用户留言模块")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "添加留言")
    @PostMapping("/messages")
    private Result saveMessage( @RequestBody MessageVO messageVO) {
        messageService.saveMessage(messageVO);
        return  Result.ok().message("留言成功");
    }

    @ApiOperation(value = "查看留言列表")
    @GetMapping("/getMessageList")
    private Result getMessageList(@RequestParam(value = "current" ,required = false) Integer current,
                               @RequestParam(value = "size",required = false) Integer size,
                               @RequestParam(value = "nickname",required = false) String nickname) {

        IPage<Message> page = messageService.getMessageList(current, size, nickname);
        long total = page.getTotal();
        List<Message> data = page.getRecords();
        return  Result.ok().data("total", total).data("data", data);
    }
}

