package com.zm.news.controller;

import com.zm.news.entity.News;
import com.zm.news.entity.User;
import com.zm.news.redis.NewsKey;
import com.zm.news.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.controller
 * @className: AdminController
 * @author: ZM
 * @description: 后台控制器
 * @date: 2021/1/25 13:48
 * @version: 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private Logger logger = LogUtils.getInstance(this.getClass());

    @RequestMapping({"/", "/index"})
    public String toAdmin() {
        if(session.getAttribute("admin") == null){
            return "admin/login";
        }
        return "admin/index";
    }

    @PostMapping("/login")
    @ResponseBody
    public Boolean login(User user) {
        User users = userService.checkUserNameAndPassword(user);
        if(users != null && users.getRoleId() == 1) {
            users.setPassword(null);
            session.setAttribute("admin", users);
            return true;
        }
        return false;
    }

    @GetMapping("/loginOut")
    @ResponseBody
    public Boolean loginOut() {
        session.setAttribute("admin", null);
        return true;
    }

    @RequestMapping("/toWelcome")
    public String toWelcome(Model model) {
        //用户数量
        model.addAttribute("userNumber", userService.totalNumberOfUsers());
        //评论数量
        model.addAttribute("commentsNumber", commentService.totalNumberOfComments());
        //新闻统计
        model.addAttribute("newsNumber",newsService.totalCount());
        //浏览量统计
        return "admin/welcome";
    }


    @RequestMapping("/toUser")
    public String toUser() {
        return "admin/user/user-list";
    }


    @RequestMapping("/toNews")
    public String toNews() {
        return "admin/news/news-list";
    }

    @RequestMapping("/toType")
    public String toType() {
        return "admin/type/type-list";
    }

    @RequestMapping("/toTag")
    public String toTag() {
        return "admin/tag/tag-list";
    }

    @RequestMapping("/toComment")
    public String toComment() {
        return "admin/comment/comment-list";
    }

    @RequestMapping("/toFriend")
    public String toFriend() {
        return "admin/friend/friend-list";
    }

    @RequestMapping("/toStatics")
    public String toStatics() {
        return "admin/statics/statics-list";
    }

    @GetMapping("/resetEs")
    @ResponseBody
    public boolean resetEs() {
        long startTime = System.currentTimeMillis();
        // 先清空再添加
        newsRepository.deleteAll();
        List<News> newsList = newsService.queryByStatus(1);
        for (News news : newsList) {
            newsRepository.save(news);
        }
        long endTime = System.currentTimeMillis();
        logger.warn("【ElasticSearch】结束保存文章数据到ES，"+"程序运行时间：" + (endTime - startTime) + "ms");
        redis.set(NewsKey.getESCount.getPrefix(), newsList.size()+"", (long) (60 * 60 * 24));
        return true;
    }
}
