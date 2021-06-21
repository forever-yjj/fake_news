package com.zm.news.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: news
 * @package: com.zm.news.utils
 * @className: LoginRequired
 * @author: ZM
 * @description: 是否需要登录 注解
 * @date: 2021/2/2 10:17
 * @version: 1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}
