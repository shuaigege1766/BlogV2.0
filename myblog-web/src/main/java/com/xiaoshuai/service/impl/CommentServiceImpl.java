package com.xiaoshuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoshuai.dto.*;
import com.xiaoshuai.entity.Comment;
import com.xiaoshuai.entity.User;
import com.xiaoshuai.mapper.CommentMapper;
import com.xiaoshuai.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai.utils.HTMLUtil;
import com.xiaoshuai.utils.UserUtil;
import com.xiaoshuai.vo.CommentVO;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public PageDTO<CommentDTO> listComments(Integer articleId, Integer current) {
        //查询文章评论量
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(articleId != null, "article_id", articleId)
                .isNull(articleId == null, "article_id").isNull("parent_id")
                .eq("is_delete", 0);
        Integer commentCount = this.baseMapper.selectCount(queryWrapper);
        if (commentCount == 0) {
            return null;
        }
        //查询评论集合
        List<CommentDTO> commentDTOList = this.baseMapper.listComments(articleId, (current - 1) * 10);
          //点赞量和评论量加入redis后在实现 现在不管
        List<Integer> commentIdList = new ArrayList<>();
        for (CommentDTO commentDTO : commentDTOList) {
            commentIdList.add(commentDTO.getId());
            //封装评论点赞量
           // commentDTO.setLikeCount(likeCountMap.get(commentDTO.getId().toString()));
        }
        //根据评论id集合查询所有分页回复数据
        List<ReplyDTO> replyDTOList = this.baseMapper.listReplies(commentIdList);
//        for (ReplyDTO replyDTO : replyDTOList) {
//            //封装回复点赞量
//            replyDTO.setLikeCount(likeCountMap.get(replyDTO.getId().toString()));
//        }
        //根据评论id查询回复量
        List<ReplyCountDTO> replyCountDTOList =this.baseMapper.listReplyCountByCommentId(commentIdList);
        //将回复量封装成评论id对应回复量的map
        Map<Integer, Integer> replyCountMap = new HashMap<>(16);
        for (ReplyCountDTO replyCountDTO : replyCountDTOList) {
            replyCountMap.put(replyCountDTO.getCommentId(), replyCountDTO.getReplyCount());
        }
        //将分页回复数据和回复量封装进对应的评论
        for (CommentDTO commentDTO : commentDTOList) {
            List<ReplyDTO> replyList = new ArrayList<>();
            for (ReplyDTO replyDTO : replyDTOList) {
                if (replyDTO.getParentId().equals(commentDTO.getId())) {
                    replyList.add(replyDTO);
                }
            }
            commentDTO.setReplyDTOList(replyList);
            commentDTO.setReplyCount(replyCountMap.get(commentDTO.getId()));
        }
        return new PageDTO<CommentDTO>(commentDTOList, commentCount);
    }

    @Override
    public void saveComment(CommentVO commentVO) {
         //这里要判断一下 看他是不是禁言状态 可以根据security来获取登录后的用户信息
        //过滤html标签
        commentVO.setCommentContent(HTMLUtil.deleteCommentTag(commentVO.getCommentContent()));
        Comment comment = new Comment();
        //获取当前登录用户id
        User user = UserUtil.getLoginUser();
        Integer userId = user.getUserId();
        comment.setUserId(userId);
        comment.setArticleId(commentVO.getArticleId());
        comment.setCommentContent(commentVO.getCommentContent());
        comment.setParentId(commentVO.getParentId());
        comment.setReplyId(commentVO.getReplyId());
        comment.setCreateTime(new Date());
        this.baseMapper.insert(comment);
    }

    /**
     * 查看评论下的回复
     *
     * @param commentId 评论id
     * @param current   当前页码
     * @return 回复列表
     */
    @Override
    public List<ReplyDTO> listRepliesByCommentId(Integer commentId, Long current) {
        //转换页码查询评论下的回复
        List<ReplyDTO> replyDTOList = this.baseMapper.listRepliesByCommentId(commentId, (current - 1) * 5);
        //查询redis的评论点赞数据
        
        return replyDTOList;
    }


    @Override
    public List<CommentBackDTO> getUserReplyList(Integer current, Integer size, String nickname) {
        List<CommentBackDTO> replyList = this.baseMapper.getUserReplyList((current - 1) * size, size, nickname);

        return replyList;
    }
}
