package com.zm.news.config;

import com.zm.news.interceptor.LoginIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: news
 * @package: com.zm.news.config
 * @className: MvcConfig
 * @author: ZM
 * @description: SpringMVC配置类
 * @date: 2021/1/28 11:56
 * @version: 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercept());
    }
}
