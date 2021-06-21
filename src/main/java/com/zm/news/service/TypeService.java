package com.zm.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.Type;
import com.zm.news.utils.ResponseCode;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service
 * @className: TypeService
 * @author: ZM
 * @description: 分类业务层接口
 * @date: 2021/1/20 10:58
 * @version: 1.0
 */
public interface TypeService {
    /**
     * 分类列表展示
     * @param page 当前页
     * @param limit 每页信息条数
     * @param searchParams 搜索json
     * @return layUi响应体
     */
    ResponseCode findTypeList(int page, int limit, String searchParams) throws JsonProcessingException;

    /**
     * 分类列表（无条件）
     * @return json字符串
     */
    String findTypeList();

    /**
     * 分类列表
     * @return java.util.List
     */
    List<Type> typeList() throws JsonProcessingException;

    /**
     * 分类列表
     * @param page 当前页
     * @param limit 数量
     * @return java.util.List
     */
    List<Type> typeList(int page, int limit) throws JsonProcessingException;

    /**
     * 回显
     * @param typeId 分类id
     * @return com.zm.news.entity.Type
     */
    Type findTypeByTypeId(Integer typeId);

    /**
     * 新增分类
     * @param params json
     * @return java.lang
     */
    Boolean addType(String params) throws JsonProcessingException;

    /**
     * 修改分类
     * @param params json
     * @return java.lang
     */
    Boolean editType(String params) throws JsonProcessingException;

    /**
     * 删除
     * @param typeId 分类id
     * @return java.lang
     */
    Boolean removeType(Integer typeId);

    /**
     * 批量删除
     * @param typeIds 分类id json
     * @return java.lang
     */
    Boolean removeTypes(String typeIds) throws JsonProcessingException;
}
