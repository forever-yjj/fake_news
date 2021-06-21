package com.zm.news.redis;

/**
 * @projectName: news
 * @package: com.zm.news.redis
 * @className: TypeKey
 * @author: ZM
 * @description: 分类关键字
 * @date: 2021/1/28 10:53
 * @version: 1.0
 */
public class TypeKey extends BasePrefix{

    private TypeKey(String prefix) {
        super(prefix);
    }

    public static TypeKey getIndex = new TypeKey("index");
    public static TypeKey getType = new TypeKey("type");
    public static TypeKey getById = new TypeKey("id");
    public static TypeKey getCount = new TypeKey("count");
}
