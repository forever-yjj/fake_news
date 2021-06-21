package com.zm.news.entity.dto;

import java.util.Arrays;

/**
 * @projectName: news
 * @package: com.zm.news.entity.dto
 * @className: LogInfo
 * @author: ZM
 * @description: 日志实体类
 * @date: 2021/2/2 11:49
 * @version: 1.0
 */
public class LogInfo {

    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;

    public LogInfo() {
    }

    public LogInfo(String ip, String url, String httpMethod, String classMethod, Object requestParams) {
        this.ip = ip;
        this.url = url;
        this.httpMethod = httpMethod;
        this.classMethod = classMethod;
        this.requestParams = requestParams;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public Object getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Object requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public String toString() {
        return "Log{" +
                "ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", requestParams=" + requestParams +
                '}';
    }
}
