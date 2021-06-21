package com.zm.news.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.news.elasticsearch.NewsRepository;
import com.zm.news.mapper.*;
import com.zm.news.utils.RedisUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.service
 * @className: BaseService
 * @author: ZM
 * @description: 业务层基类
 * @date: 2021/1/22 16:46
 * @version: 1.0
 */
public class BaseService {

    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected NewsMapper newsMapper;
    @Autowired
    protected TypeMapper typeMapper;
    @Autowired
    protected TagMapper tagMapper;
    @Autowired
    protected CommentMapper commentMapper;
    @Autowired
    protected StatisticsMapper statisticsMapper;
    @Autowired
    protected FriendMapper friendMapper;
    @Autowired
    protected NewsRepository newsRepository;


    @Autowired
    protected RedisUtils redis;
    @Autowired
    protected RestHighLevelClient client;
    @Autowired
    protected RabbitTemplate rabbitTemplate;



    protected ObjectMapper objectMapper = new ObjectMapper();
    protected Random random = new Random();
}
