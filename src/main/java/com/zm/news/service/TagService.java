package com.zm.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.Tag;
import com.zm.news.utils.ResponseCode;

import java.util.HashMap;
import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service
 * @className: TagService
 * @author: ZM
 * @description: 标签业务层接口
 * @date: 2021/1/20 16:12
 * @version: 1.0
 */
public interface TagService {

    /**
     * 标签列表展示
     * @param page 当前页
     * @param limit 每页信息条数
     * @param searchParams 搜索json
     * @return layUi响应体
     */
    ResponseCode findTagList(int page, int limit, String searchParams) throws JsonProcessingException;

    /**
     * 根据新闻id查询tag
     * @param newsId 新闻id
     * @return java.util.HashMap
     */
    HashMap<String, Object> findTagByNewsId(Integer newsId);

    /**
     * 根据新闻id查找
     * @param newsId 新闻id
     * @return java.util.List
     */
    List<Tag> findByNewsId(Integer newsId);

    /**
     * tag列表
     * @param page 当前页
     * @param limit 每页信息条数
     * @param tagName 搜索条件
     * @return layUi响应体
     */
    ResponseCode findTagListAll(int page, int limit, String tagName);

    /**
     * 标签列表
     * @return java.util.List
     */
    List<Tag> tagList();

    /**
     * 标签列表
     * @param limit 每页的信息条数
     * @return java.util.List
     */
    List<Tag> tagList(int limit);


    /**
     * 回显
     * @param tagId 标签id
     * @return com.zm.news.entity.Tag
     */
    Tag findTagByTagId(Integer tagId);

    /**
     * 新增分类
     * @param params json
     * @return java.lang
     */
    Boolean addTag(String params) throws JsonProcessingException;

    /**
     * 修改分类
     * @param params json
     * @return java.lang
     */
    Boolean editTag(String params) throws JsonProcessingException;

    /**
     * 删除
     * @param tagId 标签id
     * @return java.lang
     */
    Boolean removeTag(Integer tagId);

    /**
     * 批量删除
     * @param tagIds 标签id json
     * @return java.lang
     */
    Boolean removeTags(String tagIds) throws JsonProcessingException;

}
