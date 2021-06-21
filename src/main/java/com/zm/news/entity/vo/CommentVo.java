package com.zm.news.entity.vo;

import com.zm.news.entity.Comment;
import com.zm.news.entity.News;
import com.zm.news.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.entity.vo
 * @className: CommentVo
 * @author: ZM
 * @description: 评论实体类
 * @date: 2021/1/22 11:45
 * @version: 1.0
 */
public class CommentVo extends Comment {

    private String dateRange;

    private Date dateStart;

    private Date dateEnd;

    private String nickName;
    private String newsTitle;

    public CommentVo() {
    }

    public CommentVo(Integer commentId, String commentContent, Integer userId, Integer parentCommentId, Integer newsId, Date commentDate, String nickname, User user, News news, Comment parentComment, List<Comment> replyComments, String dateRange, Date dateStart, Date dateEnd, String nickName, String newsTitle) {
        super(commentId, commentContent, userId, parentCommentId, newsId, commentDate, nickname, user, news, parentComment, replyComments);
        this.dateRange = dateRange;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.nickName = nickName;
        this.newsTitle = newsTitle;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}
