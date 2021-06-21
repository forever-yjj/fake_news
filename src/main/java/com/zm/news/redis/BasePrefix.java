package com.zm.news.redis;

/**
 * @projectName: news
 * @package: com.zm.news.redis
 * @className: BasePrefix
 * @author: ZM
 * @description: 前缀 - 基类
 * @date: 2021/1/28 10:44
 * @version: 1.0
 */
public abstract class BasePrefix implements KeyPrefix {

    /**
     * 前缀
     */
    private String prefix;

    public BasePrefix( String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getPrefix() {

        String classname = getClass().getSimpleName();
        return classname.replace("Key", "") + ":" + prefix;
    }
}
