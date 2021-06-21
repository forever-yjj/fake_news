package com.zm.news.utils;

import net.sf.ehcache.Element;
import net.sf.ehcache.store.compound.ReadWriteCopyStrategy;

import java.io.Serializable;

/**
 * @projectName: news
 * @package: com.zm.news.utils
 * @className: CopyStrategy
 * @author: ZM
 * @description: 缓存策略 优化缓存策略
 *  序列化要缓存的对象，否则设置  copyOnRead="true" copyOnWrite="true" 缓存对象的时候可能报序列化错误
 * @date: 2021/3/9 8:27
 * @version: 1.0
 */
public class CopyStrategy implements ReadWriteCopyStrategy<Element> {

    @Override
    public Element copyForWrite(Element value) {
        if (value != null) {
            Object temp = (Serializable) value.getObjectValue();
            return new Element(value.getObjectKey(), temp);
        }
        return value;
    }

    @Override
    public Element copyForRead(Element storedValue) {
        if (storedValue != null) {
            Object temp = (Serializable) storedValue.getObjectValue();
            return new Element(storedValue.getObjectKey(), temp);
        }
        return storedValue;
    }
}
