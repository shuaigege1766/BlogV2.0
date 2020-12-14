package com.xiaoshuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.dto.CommentBackDTO;
import com.xiaoshuai.dto.CommentDTO;
import com.xiaoshuai.dto.PageDTO;
import com.xiaoshuai.dto.ReplyDTO;
import com.xiaoshuai.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai.vo.CommentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-01
 */
public interface CommentService extends IService<Comment> {
     PageDTO<CommentDTO> listComments(Integer articleId, Integer current);

     void saveComment(CommentVO commentVO);

     /**
      * 查看评论下的回复
      *
      * @param commentId 评论id
      * @param current   当前页码
      * @return 回复列表
      */
     List<ReplyDTO> listRepliesByCommentId(Integer commentId, Long current);


      List<CommentBackDTO>  getUserReplyList (Integer current, Integer size , String nickname);

}
