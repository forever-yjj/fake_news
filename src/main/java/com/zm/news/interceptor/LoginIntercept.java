package com.zm.news.interceptor;

import com.zm.news.utils.LogUtils;
import com.zm.news.annotation.LoginRequired;
import org.slf4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @projectName: news
 * @package: com.zm.news.config
 * @className: LoginIntercept
 * @author: ZM
 * @description: 登录拦截器
 * @date: 2021/2/2 10:22
 * @version: 1.0
 */
public class LoginIntercept extends HandlerInterceptorAdapter {

    private Logger logger = LogUtils.getInstance(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        // 判断接口是否需要登录
        if(methodAnnotation != null) {
            if(request.getSession().getAttribute("user") == null) {
                logger.error("未登录，跳转到登录页面");
                response.sendRedirect("/system/toLogin");
                return false;
            }
            return true;
        }
        return true;
    }
}
