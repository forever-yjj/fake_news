package com.zm.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.User;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.controller
 * @className: UserController
 * @author: ZM
 * @description: 用户控制层
 * @date: 2021/1/18 19:26
 * @version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private HashMap<String, Object> map = new HashMap<>();

    @GetMapping("/toUserList")
    @ResponseBody
    public ResponseCode toUserList(int page, int limit, String searchParams) throws JsonProcessingException {
        return userService.findUserList(page, limit, searchParams);
    }

    @PostMapping("/queryUsername")
    @ResponseBody
    public HashMap<String, Object> queryUsername(String username) {

        Boolean flag = userService.findUsername(username);
        map.put("flag", flag);
        return map;
    }

    @PostMapping("/addUser")
    @ResponseBody
    public Boolean addUser(String params) throws JsonProcessingException {
        return userService.register(params);
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "admin/user/user-add";
    }

    @GetMapping("/toEditUser")
    public String toEditUser(Integer userId, Model model) {
        User userById = userService.findUserById(userId);
        model.addAttribute("user", userById);
        return "admin/user/user-edit";
    }

    @PostMapping("/editUser")
    @ResponseBody
    public Boolean editUser(String value) throws JsonProcessingException {
        return userService.editUser(value);
    }

    @PostMapping("/editPassword")
    @ResponseBody
    public Boolean editPassword(String password, String newPassword) {
        User user = (User) session.getAttribute("user");
        user.setPassword(password);
        Boolean flag = userService.editPassword(user, newPassword);
        if(flag) {
            session.setAttribute("user", null);
            return true;
        }
        return false;
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public Boolean changeStatus(Integer userId, Integer userStatus) {
        return userService.editStatus(userId, userStatus);
    }

    @GetMapping("/removeUser")
    @ResponseBody
    public Boolean removeUser(Integer userId) {
        return userService.removeUser(userId);
    }

    @GetMapping("/removeUsers")
    @ResponseBody
    public Boolean removeUsers(String userIds) throws JsonProcessingException {
        return userService.removeUsers(userIds);
    }
}
