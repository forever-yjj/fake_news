package com.zm.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.User;
import com.zm.news.service.BaseService;
import com.zm.news.service.UserService;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service.impl
 * @className: UserServiceImpl
 * @author: ZM
 * @description: 用户业务层实现类
 * @date: 2021/1/18 19:29
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public ResponseCode findUserList(int page, int limit, String searchParams) throws JsonProcessingException {
        //开启分页
        PageHelper.startPage(page, limit);
        User user = new User();
        //模糊查询
        if (searchParams != null) {
            user = objectMapper.readValue(searchParams, User.class);
        }
        List<User> userList = userMapper.findUserList(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return ResponseCode.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public int totalNumberOfUsers() {
        return userMapper.getCount();
    }

    @Override
    public User checkUserNameAndPassword(User user) {
        return userMapper.findByUsernameAndPassword(user.getUsername(),DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
    }

    @Override
    public Boolean findUsername(String username) {
        return userMapper.findByUsername(username) == null;
    }

    @Override
    public Boolean register(String params) throws JsonProcessingException {
        User user = null;
        user = objectMapper.readValue(params, User.class);

        //MD5加密
        String newPassword = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(newPassword);

        //设置用户的初始状态和角色
        user.setRoleId(0);
        user.setUserStatus(0);
        userMapper.insertUser(user);
        return true;
    }

    @Override
    public Boolean register(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setUserStatus(0);
        user.setRoleId(0);
        user.setSex(0);
        user.setAge(0);

        return userMapper.insertUser(user) == 1;
    }

    @Override
    public User findUserById(Integer userId) {
        return userMapper.findByPrimaryKey(userId);
    }

    @Override
    public Boolean editUser(String value) throws JsonProcessingException {
        User user = objectMapper.readValue(value, User.class);
        userMapper.updateByPrimaryKey(user);
        return true;
    }

    @Override
    public Boolean editStatus(Integer userId, Integer userStatus) {
        User user = new User();
        user.setUserId(userId);
        user.setUserStatus(userStatus);
        return userMapper.updateByPrimaryKey(user) == 1;
    }

    @Override
    public Boolean editPassword(User user, String newPassword) {
        User u = userMapper.findByUsernameAndPassword(user.getUsername(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        if(u != null) {
            user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            userMapper.updateByPrimaryKey(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeUser(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId) == 1;
    }

    @Override
    public Boolean removeUsers(String userIds) throws JsonProcessingException {
        List<User> users = objectMapper.readValue(userIds, new TypeReference<List<User>>() {});
        userMapper.deleteByPrimaryKeys(users);
        return true;
    }
}
