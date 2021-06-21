package com.zm.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.News;
import com.zm.news.utils.ResponseCode;

import java.io.IOException;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service
 * @className: NewsService
 * @author: ZM
 * @description: 新闻业务层接口
 * @date: 2021/1/21 10:08
 * @version: 1.0
 */
public interface NewsService {

    /**
     * 新闻列表 or 模糊查询
     * @param page 当前页
     * @param limit 每页信息条数
     * @param news 搜索
     * @return layUi响应体
     */
    ResponseCode findNewsList(int page, int limit, News news);

    /**
     * 后台查询
     * 根据新闻id查找
     * @param newsId 新闻id
     * @return com.zm.news.entity.News
     */
    News findNewsByNewsId(Integer newsId);

    /**
     * 前台展示
     * 根据id查找新闻详情
     * @param id 新闻id
     * @return com.zm.news.entity.News
     */
    News findNewsDetailById(Integer id);

    /**
     * 新闻浏览量自增
     * @param newsId 新闻id
     */
    void increasedViews(Integer newsId);

    /**
     * 根据分类id查询新闻
     * @param id 分类id
     * @param page 当前页
     * @return PageInfo
     */
    PageInfo<News> findNewsByTypeId(Integer id, Integer page);

    /**
     * 模糊查找
     * @param page 当前页
     * @param query 搜索关键字
     * @return pageInfo
     */
    PageInfo<News> fuzzyQuery(int page, String query) throws IOException;

    /**
     * 获取es中新闻数量
     * @return 数量
     */
    long getEsDocCount() throws IOException;

    /**
     * 初始化es
     */
    void initEs();

    /**
     * 根据发表时间降序获取新闻
     * @param page 当前页码
     * @param limit 每页条数
     * @return PageInfo
     */
    PageInfo<News> orderByPublishTime(Integer page, int limit) throws JsonProcessingException;

    /**
     * 根据新闻状态查询
     * @param i 新闻状态码
     * @return java.util.List
     */
    List<News> queryByStatus(int i);

    /**
     * 查询浏览量高的新闻
     * @return java.util.List
     */
    List<News> orderByViews();

    /**
     * 根据评论数量来排序
     * @return java.util.List
     */
    List<News> orderByCommentCount();

    /**
     * 添加新闻
     * @param params json
     * @return java.lang
     */
    Boolean addNews(String params) throws JsonProcessingException;

    /**
     * 修改新闻
     * @param params json
     * @return java.lang
     */
    Boolean editNews(String params) throws JsonProcessingException;


    /**
     * 修改新闻状态
     * @param newsId 新闻id
     * @param newsStatus 新闻状态
     * @return java.lang
     */
    Boolean editStatus(Integer newsId, Integer newsStatus);

    /**
     * 删除新闻
     * @param newsId 新闻id
     * @return java.lang
     */
    Boolean removeNews(Integer newsId);

    /**
     * 批量删除
     * @param newsIds json
     * @return java.lang
     * @throws JsonProcessingException 转换异常
     */
    Boolean removeNewsT(String newsIds) throws JsonProcessingException;

    /**
     * 新闻发布 统计
     * @return java.lang
     */
    Integer totalCount();
}
