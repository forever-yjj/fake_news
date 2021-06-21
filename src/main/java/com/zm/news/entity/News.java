package com.zm.news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.entity
 * @className: News
 * @author: ZM
 * @description: 新闻实体类
 * @date: 2021/1/15 12:03
 * @version: 1.0
 */
@Document(indexName = "fake-news")
@JsonIgnoreProperties(value = { "handler"})
public class News implements Serializable {

    private static final long serialVersionUID = -22440854516264682L;

    @Id
    private Integer newsId;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String newsTitle;

    @Field(type = FieldType.Integer)
    private Integer newsType;

    private String newsKeyword;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String newsContent;

    private String newsImage;

    @Field(type = FieldType.Integer)
    private Integer newsViews;

    private String newsEditor;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String newsDesc;

    private String newsSource;

    @Field(store = false)
    private Integer newsStatus;

    @Field(type = FieldType.Date, format=DateFormat.custom,pattern ="yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd",timezone="GMT+8")
    private Date newsDate;

    @Field(type = FieldType.Integer)
    private Integer newsCommentCount;

    /**
     * 嵌套对象
     */
    @Field(type = FieldType.Object)
    private Type type;

    /**
     * 用于生成第三方表id
     */
    @Field(store = false)
    private String tagIds;

    @Field(store = false)
    private List<Comment> comments = new ArrayList<>();

    @Field(store = false)
    private List<Type> typeList = new ArrayList<>();

    @Field(store = false)
    private List<Tag> tagList = new ArrayList<>();

    public News() {
    }

    public News(Integer newsId, String newsTitle, Integer newsType, String newsKeyword, String newsContent, String newsImage, Integer newsViews, String newsEditor, String newsDesc, String newsSource, Integer newsStatus, Date newsDate, Integer newsCommentCount, String tagIds, Type type, List<Comment> comments, List<Type> typeList, List<Tag> tagList) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsType = newsType;
        this.newsKeyword = newsKeyword;
        this.newsContent = newsContent;
        this.newsImage = newsImage;
        this.newsViews = newsViews;
        this.newsEditor = newsEditor;
        this.newsDesc = newsDesc;
        this.newsSource = newsSource;
        this.newsStatus = newsStatus;
        this.newsDate = newsDate;
        this.newsCommentCount = newsCommentCount;
        this.tagIds = tagIds;
        this.type = type;
        this.comments = comments;
        this.typeList = typeList;
        this.tagList = tagList;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public String getNewsKeyword() {
        return newsKeyword;
    }

    public void setNewsKeyword(String newsKeyword) {
        this.newsKeyword = newsKeyword;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public Integer getNewsViews() {
        return newsViews;
    }

    public void setNewsViews(Integer newsViews) {
        this.newsViews = newsViews;
    }

    public String getNewsEditor() {
        return newsEditor;
    }

    public void setNewsEditor(String newsEditor) {
        this.newsEditor = newsEditor;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public Integer getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(Integer newsStatus) {
        this.newsStatus = newsStatus;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public Integer getNewsCommentCount() {
        return newsCommentCount;
    }

    public void setNewsCommentCount(Integer newsCommentCount) {
        this.newsCommentCount = newsCommentCount;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsType=" + newsType +
                ", newsKeyword='" + newsKeyword + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsImage='" + newsImage + '\'' +
                ", newsViews=" + newsViews +
                ", newsEditor='" + newsEditor + '\'' +
                ", newsDesc='" + newsDesc + '\'' +
                ", newsSource='" + newsSource + '\'' +
                ", newsStatus=" + newsStatus +
                ", newsDate=" + newsDate +
                ", newsCommentCount=" + newsCommentCount +
                ", tagIds='" + tagIds + '\'' +
                ", type=" + type +
                ", comments=" + comments +
                ", typeList=" + typeList +
                ", tagList=" + tagList +
                '}';
    }
}
