package com.zm.news.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName: news
 * @package: com.zm.news.utils
 * @className: LogUtils
 * @author: ZM
 * @description: 日志生成类 单例模式
 * @date: 2021/2/2 10:41
 * @version: 1.0
 */
public class LogUtils {

    private static Logger logger;

    private LogUtils() {}

    public static Logger getInstance(Class c) {
        return logger = LoggerFactory.getLogger(c);
    }
}
