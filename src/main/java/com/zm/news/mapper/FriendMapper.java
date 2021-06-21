package com.zm.news.mapper;

import com.zm.news.entity.Friend;
import com.zm.news.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.mapper
 * @className: FriendMapper
 * @author: ZM
 * @description: 友链持久层接口
 * @date: 2021/1/17 11:33
 * @version: 1.0
 */
@Mapper
public interface FriendMapper {

    /**
     * 友链列表
     * @return java.util.List
     */
    List<Friend> findFriendList();

    /**
     * 获取新闻列表 or 模糊查询
     * @param friend 搜索条件
     * @return java.util.List
     */
    List<Friend> findFriendByFuzzy(Friend friend);

    /**
     * 根据主键查找
     * @param friendId 友链id
     * @return com.zm.news.entity.Friend
     */
    Friend findByPrimaryKey(int friendId);

    /**
     * 新增友链
     * @param friend 友链实体类
     * @return java.lang
     */
    int insertFriend(Friend friend);

    /**
     * 根据逐渐修改
     * @param friend 友链实体类
     * @return java.lang
     */
    int updateByPrimaryKey(Friend friend);

    /**
     * 根据主键删除
     * @param friendId 友链id
     * @return java.lang
     */
    int deleteByPrimaryKey(int friendId);

    /**
     * 批量删除
     * @param friendList ids
     * @return 受影响的信息条数
     */
    int deleteByBatch(List<Friend> friendList);

    /**
     * 统计友链总数
     * @return java.lang
     */
    int getCount();

}
