package com.zm.news.redis;

import com.zm.news.entity.News;

/**
 * @projectName: news
 * @package: com.zm.news.redis
 * @className: NewsKey
 * @author: ZM
 * @description: 新闻关键字
 * @date: 2021/1/28 10:47
 * @version: 1.0
 */
public class NewsKey extends BasePrefix {

    private NewsKey(String prefix) {
        super(prefix);
    }

    public static NewsKey getIndex = new NewsKey("index");
    public static NewsKey getById = new NewsKey("id");
    public static NewsKey getByTitle = new NewsKey("title");
    public static NewsKey getByViews = new NewsKey("view");
    public static NewsKey getESCount = new NewsKey("es");
}
