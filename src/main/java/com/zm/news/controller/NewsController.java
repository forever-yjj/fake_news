package com.zm.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.News;
import com.zm.news.utils.CacheUtils;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.controller
 * @className: NewsController
 * @author: ZM
 * @description: 新闻控制层
 * @date: 2021/1/21 10:05
 * @version: 1.0
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{

    /**
     * 前台 新闻详情页 跳转
     */
    @GetMapping("/{id}")
    public String newsById(@PathVariable Integer id, Model model) throws JsonProcessingException {
        //统计访客
        statisticsService.update(request, id);
        model.addAttribute("news", newsService.findNewsDetailById(id));
        model.addAttribute("tags", tagService.findByNewsId(id));
        //新闻浏览量 自增
        newsService.increasedViews(id);
        return "view/news";
    }

    @GetMapping("/toAdd")
    public String toAdd() {
        return "admin/news/news-add";
    }

    @GetMapping("/toNewsList")
    @ResponseBody
    public ResponseCode toNewsList(int page, int limit, News news) {
        return newsService.findNewsList(page, limit, news);
    }

    @PostMapping("/addNews")
    @ResponseBody
    public Boolean addNews(String params) throws JsonProcessingException {
        return newsService.addNews(params);
    }

    @GetMapping("/toEditNews")
    public String toEditNews(Integer newsId, Model model) {
        model.addAttribute("tags", tagService.findTagByNewsId(newsId));
        model.addAttribute("news", newsService.findNewsByNewsId(newsId));
        return "admin/news/news-edit";
    }

    /**
     * 新闻发布 / 草稿
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public Boolean changeStatus(Integer newsId, Integer newsStatus) {
        Boolean flag = newsService.editStatus(newsId, newsStatus);
        if(newsStatus == 1) {
            CacheUtils.cleanNewsCache(redis,null);
            rabbitTemplate.convertAndSend("es", "news.save", newsId);
        }
        return flag;
    }

    @PostMapping("/editNews")
    @ResponseBody
    public Boolean editNews(String params) throws JsonProcessingException {
        return newsService.editNews(params);
    }

    @GetMapping("/removeNews")
    @ResponseBody
    public Boolean removeNews(Integer newsId) {
        Boolean flag = newsService.removeNews(newsId);
        CacheUtils.cleanNewsCache(redis, newsId);
        rabbitTemplate.convertAndSend("es", "news.delete", newsId);
        return flag;
    }

    @GetMapping("/removeNewsT")
    @ResponseBody
    public Boolean removeNewsT(String newsIds) throws JsonProcessingException {
        return newsService.removeNewsT(newsIds);
    }
}
