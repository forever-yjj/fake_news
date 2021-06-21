package com.zm.news.utils;

import com.zm.news.redis.NewsKey;

/**
 * @projectName: news
 * @package: com.zm.news.utils
 * @className: CacheUtils
 * @author: ZM
 * @description: 缓存管理类
 * @date: 2021/2/10 10:19
 * @version: 1.0
 */
public class CacheUtils {
    public static void cleanNewsCache(RedisUtils redis, Integer newsId) {
        redis.remove(NewsKey.getIndex.getPrefix());
        redis.remove(NewsKey.getByTitle.getPrefix());
        redis.remove(NewsKey.getByViews.getPrefix());
        redis.remove(NewsKey.getESCount.getPrefix());
        if(newsId != null) {
            redis.remove(NewsKey.getById.getPrefix()+ ":" + newsId);
        }
    }

    /**
     * 清空Redis数据库
     */
    public static void flush(RedisUtils redis){
        redis.remove("*");
    }
}
