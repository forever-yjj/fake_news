package com.zm.news.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: news
 * @package: com.zm.news.entity
 * @className: Statistics
 * @author: ZM
 * @description: 日志 实体类
 * @date: 2021/2/2 11:00
 * @version: 1.0
 */
public class Statistics implements Serializable{

    private Integer id;

    private String ip;

    private Integer requestCount;

    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date requestDate;

    private Integer location;


    public Statistics() {
    }

    public Statistics(Integer id, String ip, Integer requestCount, Date requestDate, Integer location) {
        this.id = id;
        this.ip = ip;
        this.requestCount = requestCount;
        this.requestDate = requestDate;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", requestCount=" + requestCount +
                ", requestDate=" + requestDate +
                ", location=" + location +
                '}';
    }
}
