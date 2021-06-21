package com.zm.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.Statistics;
import com.zm.news.utils.ResponseCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: news
 * @package: com.zm.news.service
 * @className: StatisticsService
 * @author: ZM
 * @description: 统计业务层接口
 * @date: 2021/2/2 12:31
 * @version: 1.0
 */
public interface StatisticsService {

    /**
     * 统计列表
     * @param page 当前页
     * @param limit 每页信息条数
     * @param statistics 统计信息
     * @param type 时间段
     * @return layUi响应体
     */
    ResponseCode findStaticsList(int page, int limit, Statistics statistics, Integer type);

    /**
     * 统计访客
     * @param request 请求体
     * @param location 位置
     * @throws JsonProcessingException
     */
    void update(HttpServletRequest request, Integer location) throws JsonProcessingException;
}
