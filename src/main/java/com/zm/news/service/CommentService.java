package com.zm.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.Comment;
import com.zm.news.entity.vo.CommentVo;
import com.zm.news.utils.ResponseCode;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service
 * @className: CommentService
 * @author: ZM
 * @description: 评论业务层接口
 * @date: 2021/1/22 11:38
 * @version: 1.0
 */
public interface CommentService {

    /**
     * 评论列表 or 模糊查询
     * @param page 当前页
     * @param limit 每页信息条数
     * @param comment 搜索条件
     * @return layUi响应体
     * @throws JsonProcessingException
     */
    ResponseCode findCommentList(int page, int limit, CommentVo comment);

    /**
     * 根据新闻id查询
     * @param id 新闻id
     * @return java.util.List
     */
    List<Comment> findCommentByNewsId(Integer id);

    /**
     * 保存评论
     * @param comment 评论
     * @return java.lang
     */
    Boolean saveComment(Comment comment);

    /**
     * 删除评论
     * @param commentId 评论id
     * @return java.lang
     */
    Boolean removeComment(Integer commentId);

    /**
     * 批量删除
     * @param commendIds 评论Id json
     * @return java.lang
     */
    Boolean removeComments(String commendIds);

    /**
     * 用户评论数统计
     * @return 信息条数
     */
    int totalNumberOfComments();

}
