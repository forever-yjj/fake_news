package com.zm.news.redis;

/**
 * @projectName: news
 * @package: com.zm.news.redis
 * @className: UserKey
 * @author: ZM
 * @description: 用户关键字
 * @date: 2021/2/1 19:17
 * @version: 1.0
 */
public class UserKey extends BasePrefix{
    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getCode = new UserKey("code");
}
