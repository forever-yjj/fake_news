package com.zm.news.mapper;

import com.zm.news.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.mapper
 * @className: NewsMapper
 * @author: ZM
 * @description: 新闻持久层接口
 * @date: 2021/1/17 10:16
 * @version: 1.0
 */
@Mapper
public interface NewsMapper {

    /**
     * 根据主键查询
     * @param newsId 新闻id
     * @return com.zm.news.entity.News
     */
    News findByPrimaryKey(int newsId);

    /**
     * 根据主键查询
     * @param newsId 新闻id
     * @return id、title
     */
    News findByPrimaryKeyNotNull(int newsId);

    /**
     * 根据分类id查询 新闻
     * @param typeId 分类id
     * @return java.util.List
     */
    List<News> findByTypeId(int typeId);

    /**
     * 已发布的新闻
     * @param status 状态码
     * @return java.util.List
     */
    List<News> findByStatus(int status);

    /**
     * 根据新闻标题和 新闻简介模糊匹配
     * @param query 关键字
     * @return java.util.List
     */
    List<News> findByFuzzy(@Param("keyword") String query);

    /**
     * 根据发布时间降序查询
     * @return java.util.List
     */
    List<News> orderByPublishTime();

    /**
     * 根据浏览量排序
     * @return java.util.List
     */
    List<News> orderByViews();

    /**
     * 根据评论数来排序
     * @return java.util.List
     */
    List<News> orderByCommentCount();

    /**
     * 获取新闻列表 or 模糊查询
     * @param news 搜索条件
     * @return java.util.List
     */
    List<News> findNewsList(News news);

    /**
     * 新增新闻
     * @param news 新闻实体类
     * @return java.lang
     */
    int insertNews(News news);

    /**
     * 根据主键更新
     * @param news 新闻实体类
     * @return java.lang
     */
    int updateByPrimaryKey(News news);

    /**
     * 浏览量更新
     * @param id 新闻id
     * @param num 浏览次数
     * @return java.lang
     */
    int updateViewsById(int id, int num);

    /**
     * 根据新闻id更新评论统计
     * @param id 新闻id
     * @return java.lang
     */
    int updateCommentById(int id);

    /**
     * 根据主键删除
     * @param newsId 新闻id
     * @return java.lang
     */
    int deleteByPrimaryKey(int newsId);

    /**
     * 批量删除
     * @param newsList newsId
     * @return java.lang
     */
    int deleteByPrimaryKeys(List<News> newsList);

    /**
     * 统计新闻总数
     * @return java.lang
     */
    int getCount();

}
