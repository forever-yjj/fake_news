package com.zm.news.entity.dto;

/**
 * @projectName: news
 * @package: com.zm.news.entity.dto
 * @className: LogErrorInfo
 * @author: ZM
 * @description: 日志异常处理类
 * @date: 2021/2/5 10:44
 * @version: 1.0
 */
public class LogErrorInfo {

    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private RuntimeException exception;

    public LogErrorInfo() {
    }

    public LogErrorInfo(String ip, String url, String httpMethod, String classMethod, Object requestParams, RuntimeException exception) {
        this.ip = ip;
        this.url = url;
        this.httpMethod = httpMethod;
        this.classMethod = classMethod;
        this.requestParams = requestParams;
        this.exception = exception;
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

    public RuntimeException getException() {
        return exception;
    }

    public void setException(RuntimeException exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "LogErrorInfo{" +
                "ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", requestParams=" + requestParams +
                ", exception=" + exception +
                '}';
    }
}
