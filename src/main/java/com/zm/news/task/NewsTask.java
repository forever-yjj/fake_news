package com.zm.news.task;

import com.zm.news.entity.News;
import com.zm.news.redis.NewsKey;
import com.zm.news.service.BaseService;
import com.zm.news.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @projectName: news
 * @package: com.zm.news.task
 * @className: NewsTask
 * @author: ZM
 * @description: 新闻定时任务
 * @date: 2021/2/3 14:33
 * @version: 1.0
 */
@Service
public class NewsTask extends BaseService {

    private Logger logger = LogUtils.getInstance(this.getClass());

    /**
     * 每天凌晨的定时任务
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void saveViews() {
        logger.warn("【定时任务】开始保存阅读量");
        long startTime = System.currentTimeMillis();
        Set<String> keys = redis.getKeys(NewsKey.getByViews.getPrefix());
        for (String key : keys) {
            String id = key.substring(key.lastIndexOf(":") + 1);
            Integer count = Integer.valueOf((String) redis.get(NewsKey.getByViews.getPrefix() + ":" + id)) ;
            newsMapper.updateViewsById(Integer.parseInt(id), count);
        }
        long endTime = System.currentTimeMillis();
        logger.warn("【定时任务】结束保存阅读量，"+"程序运行时间：" + (endTime - startTime) + "ms");
    }

    /**
     * 每天10:38
     */
    @Scheduled(cron = "0 38 10 ? * *")
    public void saveToEs(){
        logger.warn("【定时任务】开始保存文章数据到ES");
        long startTime = System.currentTimeMillis();
        // 先清空再添加
        newsRepository.deleteAll();
        List<News> newsList = newsMapper.findByStatus(1);
        for (News news : newsList) {
            newsRepository.save(news);
        }
        long endTime = System.currentTimeMillis();
        logger.warn("【定时任务】结束保存文章数据到ES，"+"程序运行时间：" + (endTime - startTime) + "ms");
        redis.set(NewsKey.getESCount.getPrefix(),newsList.size(),(long)60*60*24);
    }
}
