package com.zm.news.mapper;

import com.zm.news.entity.Comment;
import com.zm.news.entity.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.mapper
 * @className: CommentMapper
 * @author: ZM
 * @description: 评论持久层接口
 * @date: 2021/1/17 10:55
 * @version: 1.0
 */
@Mapper
public interface CommentMapper {

    /**
     * 查询评论列表
     * @param comment 评论实体类
     * @return java.util.List
     */
    List<Comment> findCommentList(CommentVo comment);

    /**
     * 根据评论id 查找
     * @param commentId 评论id
     * @param parentCommentId 父评论id
     * @return comment
     */
    Comment findCommentByPrimary(int commentId, int parentCommentId);


    /**
     * 根据创建时间倒序来排
     * @param newsId 新闻id
     * @param parentId 父评论id
     * @return java.util.List
     */
    List<Comment> findByNewsIdAndParentId(int newsId, int parentId);

    /**
     * 查询一级评论
     * @param newsId 新闻id
     * @param parentId 父评论id
     * @return java.util.List
     */
    List<Comment> findFirstLevel(int newsId, int parentId);

    /**
     * 查询二级评论
     * @param newsId 新闻id
     * @param childId 子评论id
     * @return java.util.List
     */
    List<Comment> findSecondLevel(int newsId, int childId);

    /**
     * 新增评论
     * @param comment 评论
     * @return java.lang
     */
    int insertComment(Comment comment);

    /**
     * 根据主键删除
     * @param commentId 评论id
     * @return java.lang
     */
    int deleteByPrimaryKey(int commentId);

    /**
     * 获取新闻的评论总数
     * @param newsId 新闻id
     * @return java.lang
     */
    int getCountNewsId(int newsId);

    /**
     * 获取评论总数
     * @return java.lang
     */
    int getCount();

}
