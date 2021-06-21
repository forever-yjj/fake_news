package com.zm.news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.entity
 * @className: Comment
 * @author: ZM
 * @description: 评论实体类
 * @date: 2021/1/15 12:13
 * @version: 1.0
 */
@JsonIgnoreProperties(value = { "handler"})
public class Comment implements Serializable {

    private static final long serialVersionUID = -22440854516264680L;

    private Integer commentId;

    private String commentContent;

    private Integer userId;

    private Integer parentCommentId;

    private Integer newsId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date commentDate;

    private String nickname;
    private User user;

    private News news;

    private Comment parentComment;

    private List<Comment> replyComments = new ArrayList<>();


    public Comment() {
    }

    public Comment(Integer commentId, String commentContent, Integer userId, Integer parentCommentId, Integer newsId, Date commentDate, String nickname, User user, News news, Comment parentComment, List<Comment> replyComments) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.userId = userId;
        this.parentCommentId = parentCommentId;
        this.newsId = newsId;
        this.commentDate = commentDate;
        this.nickname = nickname;
        this.user = user;
        this.news = news;
        this.parentComment = parentComment;
        this.replyComments = replyComments;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", userId=" + userId +
                ", parentCommentId=" + parentCommentId +
                ", newsId=" + newsId +
                ", commentDate=" + commentDate +
                ", nickname='" + nickname + '\'' +
                ", user=" + user +
                ", news=" + news +
                ", parentComment=" + parentComment +
                ", replyComments=" + replyComments +
                '}';
    }
}
