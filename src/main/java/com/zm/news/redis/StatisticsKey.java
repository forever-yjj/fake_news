package com.zm.news.redis;

/**
 * @projectName: news
 * @package: com.zm.news.redis
 * @className: StatisticsKey
 * @author: ZM
 * @description: 统计关键字
 * @date: 2021/2/2 12:37
 * @version: 1.0
 */
public class StatisticsKey extends BasePrefix{

    public StatisticsKey(String prefix) {
        super(prefix);
    }

    public static StatisticsKey getById = new StatisticsKey("id");
    public static StatisticsKey todayVisitor = new StatisticsKey("visitor");
    public static StatisticsKey todayRequest = new StatisticsKey("request");
    public static StatisticsKey getTotal = new StatisticsKey("total");
}
