package com.zm.news.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zm.news.entity.Statistics;
import com.zm.news.redis.StatisticsKey;
import com.zm.news.service.BaseService;
import com.zm.news.service.StatisticsService;
import com.zm.news.utils.IpUtils;
import com.zm.news.utils.ResponseCode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @projectName: news
 * @package: com.zm.news.service.impl
 * @className: StatisticsServiceImpl
 * @author: ZM
 * @description: 统计业务实现类
 * @date: 2021/2/2 12:31
 * @version: 1.0
 */
@Service
public class StatisticsServiceImpl extends BaseService implements StatisticsService {


    @Override
    public ResponseCode findStaticsList(int page, int limit, Statistics statistics, Integer type) {
        PageHelper.startPage(page, limit);
        List<Statistics> statisticsList = statisticsMapper.findByParams(statistics, type);
        PageInfo<Statistics> pageInfo = new PageInfo<>(statisticsList);
        return ResponseCode.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public void update(HttpServletRequest request, Integer location) throws JsonProcessingException {
        String ipAddress = IpUtils.getIpAddress(request);
        Statistics statistics = new Statistics();
        statistics.setIp(ipAddress);
        statistics.setLocation(location);
        statistics.setRequestDate(new Date());

        String base = StatisticsKey.getTotal.getPrefix() + ":" + ipAddress + ":" + location;
        String prefix = StatisticsKey.todayVisitor.getPrefix() + ":" + ipAddress;

        Set<String> keys = redis.getKeys(StatisticsKey.getTotal.getPrefix());
        int count = keys.size();

        if(!redis.exists(base)){
            statistics.setRequestCount(count + 1);
            redis.set(base, "");
            redis.remove(prefix);
            redis.set(prefix, objectMapper.writeValueAsString(statistics));
        }
    }
}
