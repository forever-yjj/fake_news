package com.zm.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.Friend;
import com.zm.news.service.BaseService;
import com.zm.news.service.FriendService;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.service.impl
 * @className: FriendServiceImpl
 * @author: ZM
 * @description: 友链业务层实现类
 * @date: 2021/3/10 20:16
 * @version: 1.0
 */
@Service
public class FriendServiceImpl extends BaseService implements FriendService {

    @Override
    public ResponseCode findFriendList(int page, int limit, Friend friend) {
        PageHelper.startPage(page, limit);
        List<Friend> friendList = friendMapper.findFriendByFuzzy(friend);
        PageInfo<Friend> pageInfo = new PageInfo<>(friendList);
        return ResponseCode.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public List<Friend> findFriendList() {
        PageHelper.startPage(1,4);
        List<Friend> friendList = friendMapper.findFriendList();
        PageInfo<Friend> pageInfo = new PageInfo<>(friendList);
        return pageInfo.getList();
    }

    @Override
    public Boolean insertFriend(Friend friend) {
        return friendMapper.insertFriend(friend) == 1;
    }

    @Override
    public Friend findFriendById(Integer friendId) {
        return friendMapper.findByPrimaryKey(friendId);
    }

    @Override
    public Boolean editFriend(Friend friend) {
        return friendMapper.updateByPrimaryKey(friend) == 1;
    }

    @Override
    public Boolean removeFriend(Integer friendId) {
        return friendMapper.deleteByPrimaryKey(friendId) == 1;
    }

    @Override
    public Boolean removeBatch(String friendIds) throws JsonProcessingException {
        List<Friend> friendList = objectMapper.readValue(friendIds, new TypeReference<List<Friend>>() {});
        return friendMapper.deleteByBatch(friendList) > 0;
    }
}
