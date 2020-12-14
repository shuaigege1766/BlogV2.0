package com.xiaoshuai.dto;

import lombok.Data;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-01-上午 11:23
 **/
@Data
public class ReplyCountDTO {
    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复数量
     */
    private Integer replyCount;
    
}
