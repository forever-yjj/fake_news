package com.zm.news.controller;

import com.zm.news.elasticsearch.NewsRepository;
import com.zm.news.mapper.FriendMapper;
import com.zm.news.service.*;
import com.zm.news.utils.RedisUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.controller
 * @className: BaseController
 * @author: ZM
 * @description: 基类controller
 * @date: 2021/1/18 19:26
 * @version: 1.0
 */
public class BaseController {

    @Autowired
    UserService userService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    NewsService newsService;
    @Autowired
    CommentService commentService;
    @Autowired
    StatisticsService statisticsService;
    @Autowired
    FriendService friendService;

    @Autowired
    RedisUtils redis;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    RabbitTemplate rabbitTemplate;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    protected final String ADMIN = "0";
    protected final String COMMON_USER = "1";

    /**
     * 每次requestMapping执行
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
    }
}
