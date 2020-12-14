package com.xiaoshuai.dto;

import lombok.Data;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-03-上午 10:41
 **/
@Data
public class CommentBackDTO {

    private  Integer userId;
    private  String avatar;
    private  String nickname;
    private  String replyId;
    private  String replyNickname;
    private  Integer article_id;
    private  String articleTitle;
    private  String commentContent;
    private  String createTime;
    
}
