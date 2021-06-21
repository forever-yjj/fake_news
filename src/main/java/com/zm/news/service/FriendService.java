package com.zm.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.Friend;
import com.zm.news.utils.ResponseCode;

import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.service
 * @className: FriendService
 * @author: ZM
 * @description: 友链业务层接口
 * @date: 2021/3/10 20:13
 * @version: 1.0
 */
public interface FriendService {

    /**
     * 友链列表
     * @param page 当前页
     * @param limit 每页信息条数
     * @param friend 友链
     * @return layUi响应体
     */
    ResponseCode findFriendList(int page, int limit, Friend friend);

    /**
     * 友链展示
     * @return 列表
     */
    List<Friend> findFriendList();

    /**
     * 新增有脸
     * @param friend 友链
     * @return 布尔
     */
    Boolean insertFriend(Friend friend);

    /**
     * 根据id查询
     * @param friendId id
     * @return 友链
     */
    Friend findFriendById(Integer friendId);

    /**
     * 根据id修改
     * @param friend 友链
     * @return 布尔
     */
    Boolean editFriend(Friend friend);

    /**
     * 根据id删除
     * @param friendId id
     * @return 布尔
     */
    Boolean removeFriend(Integer friendId);

    /**
     * 批量删除
     * @param friendIds ids
     * @return 布尔
     * @throws JsonProcessingException
     */
    Boolean removeBatch(String friendIds) throws JsonProcessingException;
}
