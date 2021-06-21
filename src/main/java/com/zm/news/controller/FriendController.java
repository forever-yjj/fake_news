package com.zm.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.Friend;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @projectName: news
 * @package: com.zm.news.controller
 * @className: FriendController
 * @author: ZM
 * @description: 友链控制层
 * @date: 2021/3/10 20:12
 * @version: 1.0
 */
@Controller
@RequestMapping("/friend")
public class FriendController extends BaseController {

    @GetMapping("/toFriendList")
    @ResponseBody
    public ResponseCode toFriendList(int page, int limit, Friend friend) {
        return friendService.findFriendList(page, limit, friend);
    }

    @GetMapping("/toAdd")
    public String toAdd() {
        return "admin/friend/friend-add";
    }

    @PostMapping("/addFriend")
    @ResponseBody
    public Boolean addFriend(@RequestBody Friend friend) {
        friend.setFriendDate(new Date());
        return friendService.insertFriend(friend);
    }

    @GetMapping("/toEditFriend")
    public String toEditFriend(Integer friendId, Model model) {
        Friend friend = friendService.findFriendById(friendId);
        model.addAttribute("friend", friend);
        return "admin/friend/friend-edit";
    }

    @PostMapping("/editFriend")
    @ResponseBody
    public Boolean editFriend(@RequestBody Friend friend) {
        friend.setFriendDate(new Date());
        return friendService.editFriend(friend);
    }

    @GetMapping("/removeFriend")
    @ResponseBody
    public Boolean removeFriend(Integer friendId) {
        return friendService.removeFriend(friendId);
    }

    @GetMapping("/removeFriends")
    @ResponseBody
    public Boolean removeFriends(String friendIds) throws JsonProcessingException {
        return friendService.removeBatch(friendIds);
    }
}
