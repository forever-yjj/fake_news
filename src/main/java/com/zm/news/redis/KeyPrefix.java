package com.zm.news.redis;

/**
 * @projectName: news
 * @package: com.zm.news.redis
 * @className: KeyPrefix
 * @author: ZM
 * @description: 关键字前缀 接口
 * @date: 2021/1/28 10:45
 * @version: 1.0
 */
public interface KeyPrefix {

    /**
     * 获取前缀
     * @return java.lang
     */
    String getPrefix();
}
