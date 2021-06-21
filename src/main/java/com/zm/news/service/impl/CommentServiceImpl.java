package com.zm.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.Comment;
import com.zm.news.entity.News;
import com.zm.news.entity.vo.CommentVo;
import com.zm.news.service.BaseService;
import com.zm.news.service.CommentService;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service.impl
 * @className: CommentServiceImpl
 * @author: ZM
 * @description: 评论业务层实现类
 * @date: 2021/1/22 11:40
 * @version: 1.0
 */
@Service
public class CommentServiceImpl extends BaseService implements CommentService {

    /**
     * 存放迭代找出的所有子代的集合
     */
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public ResponseCode findCommentList(int page, int limit, CommentVo comment) {

        PageHelper.startPage(page, limit);
        List<Comment> commentList = commentMapper.findCommentList(comment);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        return ResponseCode.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public List<Comment> findCommentByNewsId(Integer id) {
        //查询父级评论
        List<Comment> parentList = commentMapper.findByNewsIdAndParentId(id, -1);
        for (Comment comment : parentList) {
            Integer commentId = comment.getCommentId();
            String nickname = comment.getUser().getNickname();

            if(nickname != null) {
                comment.setNickname(nickname);
            }

            //查询子级评论
            List<Comment> childComment = commentMapper.findByNewsIdAndParentId(id, commentId);
            mergeComment(id, childComment, comment);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return parentList;
    }

    @Override
    public Boolean saveComment(Comment comment) {
        if(commentMapper.insertComment(comment) == 1) {
            newsMapper.updateCommentById(comment.getNewsId());
            return true;
        }
        return false;
    }

    private void mergeComment(Integer newsId, List<Comment> childComment, Comment parentComment) {
        if(childComment.size() > 0) {
            childComment.forEach( comment -> {
                Integer commentId = comment.getCommentId();
                comment.setNickname(comment.getUser().getNickname());
                //将父节点放入子节点
                comment.setParentComment(parentComment);
                tempReplys.add(comment);
                recursively(newsId, commentId, comment);
            });
        }
    }

    private void recursively(Integer newsId, Integer childId, Comment parentComment) {
        List<Comment> secondLevel = commentMapper.findSecondLevel(newsId, childId);
        if(secondLevel.size() > 0) {
            secondLevel.forEach(comment -> {
                Integer commentId = comment.getCommentId();
                comment.setNickname(comment.getUser().getNickname());
                comment.setParentComment(parentComment);
                tempReplys.add(comment);
                //递归
                recursively(newsId, commentId, parentComment);
            });
        }
    }

    @Override
    public Boolean removeComment(Integer commentId) {
        Comment comment = commentMapper.findCommentByPrimary(commentId, -1);
        if(comment == null) {
            commentMapper.deleteByPrimaryKey(commentId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeComments(String commendIds) {
        return null;
    }

    @Override
    public int totalNumberOfComments() {
        return commentMapper.getCount();
    }
}
