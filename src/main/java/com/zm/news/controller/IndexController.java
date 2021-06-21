package com.zm.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.News;
import com.zm.news.entity.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

/**
 * @projectName: news
 * @package: com.zm.news.controller
 * @className: IndexController
 * @author: ZM
 * @description: 首页控制器
 * @date: 2021/1/25 17:47
 * @version: 1.0
 */
@Controller
public class IndexController extends BaseController{

    /**
     *前台 主页展示
     */
    @GetMapping({"/", "/index"})
    public String index(Integer page, Model model) throws JsonProcessingException {

        //获取已发布的数量
        int totalCount = newsService.totalCount();
        int nums = (int) Math.ceil(totalCount / 10 + 0.5);
        if(ObjectUtils.isEmpty(page)) {
            page = 1;
        }else if(page < 1 || page > nums) {
            return "error/404";
        }

        //统计访客
        statisticsService.update(request, -1);
        // 获取阅读量最高的10篇文章
        List<News> views = newsService.orderByViews();
        List<News> list = newsService.orderByCommentCount();
        PageInfo<News> pageInfo = newsService.orderByPublishTime(page, 8);
        List<Type> types = typeService.typeList(1, 6);

        model.addAttribute("page", page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("newsList", pageInfo.getList());
        model.addAttribute("ranking", views);
        model.addAttribute("hot", list);
        model.addAttribute("type", types);
        model.addAttribute("friendList", friendService.findFriendList());
        return "index";
    }

    @PostMapping("/search")
    public String search(String query,@RequestParam(defaultValue = "1") Integer page, Model model) throws IOException {
        PageInfo<News> pageInfo = newsService.fuzzyQuery(page, query);
        if(page < 1) {
            return "error/404";
        }

        model.addAttribute("newsList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("page", page);
        return "view/search";
    }

    @GetMapping("/about")
    public String toAbout() {
        return "view/about";
    }
}
