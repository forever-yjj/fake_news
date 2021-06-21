package com.zm.news.entity;

import java.io.Serializable;

/**
 * @projectName: news
 * @package: com.zm.news.entity
 * @className: User
 * @author: ZM
 * @description: 用户实体
 * @date: 2021/1/15 11:56
 * @version: 1.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = -22440854516264685L;

    private Integer userId;

    private String nickname;

    private String username;

    private String password;

    /**
     * 性别 0 男 1女
     */
    private Integer sex;

    private Integer age;

    /**
     * 角色id 0 普通用户 1 管理员
     */
    private Integer roleId;

    private String address;

    private Integer userStatus;

    private String remark;

    public User() {
    }

    public User(Integer userId, String nickname, String username, String password, Integer sex, Integer age, Integer roleId, String address, Integer userStatus, String remark) {
        this.userId = userId;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.roleId = roleId;
        this.address = address;
        this.userStatus = userStatus;
        this.remark = remark;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", roleId=" + roleId +
                ", address='" + address + '\'' +
                ", userStatus=" + userStatus +
                ", remark='" + remark + '\'' +
                '}';
    }
}
