package com.zm.news.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zm.news.entity.Statistics;
import com.zm.news.redis.StatisticsKey;
import com.zm.news.service.BaseService;
import com.zm.news.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @projectName: news
 * @package: com.zm.news.task
 * @className: StatisticsTask
 * @author: ZM
 * @description: 统计 - 定时任务
 * @date: 2021/2/2 13:07
 * @version: 1.0
 */
@Service
public class StatisticsTask extends BaseService {

    private Logger logger = LogUtils.getInstance(this.getClass());

    @Scheduled(cron = "0 0 0 * * ?")
    public void saveRequest() throws JsonProcessingException {
        logger.warn("【定时任务】开始统计访客信息");
        long startTime = System.currentTimeMillis();
        Set<String> keys = redis.getKeys(StatisticsKey.todayVisitor.getPrefix());
        for (String key : keys) {
            String str = (String) redis.get(key);
            Statistics statistics = objectMapper.readValue(str, Statistics.class);
            Statistics temp = statisticsMapper.selectByIp(statistics.getIp());
            if(temp != null) {
                statisticsMapper.updateByIp(statistics);
            }else {
                statisticsMapper.insert(statistics);
            }
            redis.remove(key);
        }
        long endTime = System.currentTimeMillis();
        logger.warn("【定时任务】结束统计访客信息，"+"程序运行时间：" + (endTime - startTime) + "ms");
    }
}
