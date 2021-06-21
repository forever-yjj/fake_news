package com.zm.news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: news
 * @package: com.zm.news.entity
 * @className: Friend
 * @author: ZM
 * @description: 友链实体类
 * @date: 2021/1/15 12:18
 * @version: 1.0
 */
public class Friend implements Serializable {

    private Integer friendId;

    private String friendName;

    private String website;

    @JsonFormat(pattern ="yyyy-MM-dd",timezone="GMT+8")
    private Date friendDate;

    public Friend() {
    }

    public Friend(Integer friendId, String friendName, String website, Date friendDate) {
        this.friendId = friendId;
        this.friendName = friendName;
        this.website = website;
        this.friendDate = friendDate;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getFriendDate() {
        return friendDate;
    }

    public void setFriendDate(Date friendDate) {
        this.friendDate = friendDate;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendId=" + friendId +
                ", friendName='" + friendName + '\'' +
                ", website='" + website + '\'' +
                ", friendDate=" + friendDate +
                '}';
    }
}
