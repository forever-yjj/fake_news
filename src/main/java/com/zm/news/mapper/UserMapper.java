package com.zm.news.mapper;

import com.zm.news.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.mapper
 * @className: UserMapper
 * @author: ZM
 * @description: 用户持久层接口
 * @date: 2021/1/15 14:31
 * @version: 1.0
 */
@Mapper
public interface UserMapper {

    /**
     * 根据主键查找
     * @param userId 用户id
     * @return com.zm.news.entity.User
     */
    User findByPrimaryKey(int userId);

    /**
     * 根据主键查找
     * @param userId 用户id
     * @return id、nickname
     */
    User findByPrimaryKeyNotNull(int userId);

    /**
     * 根据username查找
     * @param username 用户名
     * @return com.zm.news.entity.User
     */
    User findByUsername(String username);

    /**
     * 根据昵称查找
     * @param nickName 昵称
     * @return java.util.List
     */
    List<User> findByNickName(String nickName);

    /**
     *用户登录校验
     * @param username 用户名
     * @param password 密码
     * @return com.zm.news.entity.User
     */
    User findByUsernameAndPassword(String username, String password);


    /**
     * 查询所有用户列表
     * @param user 模糊查询条件
     * @return java.util.List
     */
    List<User> findUserList(User user);

    /**
     * 添加用户
     * @param user 用户实体类
     * @return java.lang
     */
    int insertUser(User user);

    /**
     * 根据主键修改
     * @param user 用户实体类
     * @return java.lang
     */
    int updateByPrimaryKey(User user);

    /**
     * 根据主键删除
     * @param userId 用户id
     * @return java.lang
     */
    int deleteByPrimaryKey(int userId);

    /**
     * 根据主键批量删除
     * @param userIds 用户id
     * @return java.lang
     */
    int deleteByPrimaryKeys(List<User> userIds);

    /**
     * 统计正常状态的用户数量
     * @return nums
     */
    int getCount();
}
