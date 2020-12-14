package com.xiaoshuai.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-01-上午 10:41
 **/
@Data
public class CommentDTO {

    /**
     * 评论id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 个人网站
     */
    private String webSite;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 回复量
     */
    private Integer replyCount;

    /**
     * 回复列表
     */
    private List<ReplyDTO> replyDTOList;
    
}
