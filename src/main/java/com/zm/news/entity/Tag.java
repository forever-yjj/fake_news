package com.zm.news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.entity
 * @className: Tag
 * @author: ZM
 * @description: 标签实体类
 * @date: 2021/1/15 12:10
 * @version: 1.0
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = -22440854516264683L;

    private Integer tagId;

    private String tagName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date tagDate;


    public Tag() {
    }

    public Tag(Integer tagId, String tagName, Date tagDate) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDate = tagDate;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getTagDate() {
        return tagDate;
    }

    public void setTagDate(Date tagDate) {
        this.tagDate = tagDate;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagDate=" + tagDate +
                '}';
    }
}
