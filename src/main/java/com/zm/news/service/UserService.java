package com.zm.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.User;
import com.zm.news.utils.ResponseCode;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service
 * @className: UserService
 * @author: ZM
 * @description: 用户业务层
 * @date: 2021/1/18 19:28
 * @version: 1.0
 */
public interface UserService {

    /**
     * 用户列表展示 or 模糊查询
     * @param page 当前页
     * @param limit 每页信息条数
     * @param searchParams 查询条件 json
     * @return layUI响应体
     * @throws JsonProcessingException
     */
    ResponseCode findUserList(int page, int limit, String searchParams) throws JsonProcessingException;

    /**
     * 用户总数统计
     * @return 信息条数
     */
    int totalNumberOfUsers();

    /**
     * 检查用户信息
     * @param user 用户
     * @return com.zm.news.entity.User
     */
    User checkUserNameAndPassword(User user);

    /**
     * 查询username
     * @param username 用户名
     * @return java.lang
     */
    Boolean findUsername(String username);

    /**
     * 新增用户
     * @param params json字符串
     * @return java.lang
     * @throws JsonProcessingException
     */
    Boolean register(String params) throws JsonProcessingException;

    /**
     * 新增用户
     * @param user 用户实体类
     * @return java.lang
     */
    Boolean register(User user);

    /**
     * 数据回显
     * @param userId 用户id
     * @return com.zm.news.entity.User
     */
    User findUserById(Integer userId);

    /**
     * 用户修改
     * @param user json字符串
     * @return java.lang
     * @throws JsonProcessingException
     */
    Boolean editUser(String user) throws JsonProcessingException;

    /**
     * 管理用户状态
     * @param userId 用户id
     * @param userStatus 用户状态
     * @return java.lang
     */
    Boolean editStatus(Integer userId, Integer userStatus);

    /**
     * 修改密码
     * @param user 用户信息
     * @param newPassword 新密码
     * @return java.lang
     */
    Boolean editPassword(User user, String newPassword);

    /**
     * 删除
     * @param userId 用户id
     * @return java.lang
     */
    Boolean removeUser(Integer userId);

    /**
     * 批量删除
     * @param userIds 用户id
     * @return java.lang
     * @throws JsonProcessingException
     */
    Boolean removeUsers(String userIds) throws JsonProcessingException;

}
