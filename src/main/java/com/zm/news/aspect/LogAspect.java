package com.zm.news.aspect;

import com.zm.news.entity.dto.LogErrorInfo;
import com.zm.news.entity.dto.LogInfo;
import com.zm.news.utils.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: news
 * @package: com.zm.news.aspect
 * @className: LogAspect
 * @author: ZM
 * @description: 日志 - AOP
 * @date: 2021/2/2 10:48
 * @version: 1.0
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LogUtils.getInstance(this.getClass());

    /**
     * 定义切面
     */
    @Pointcut("execution(* com.zm.news.controller.*.*(..))" +
            "&& !execution(* com.zm.news.controller.BaseController.*(..))")
    public void log() {}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("===============================Start========================");
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Map<String, Object> requestParams = getRequestParams(joinPoint);
        LogInfo log = new LogInfo(ip, url, requestMethod, classMethod, requestParams.toString());
        logger.info("Request : {}", log);
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        logger.info("Time Cost            : {} ms", System.currentTimeMillis() - start);
        return result;
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("===============================End========================");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("Return ------ {}" , result);
    }

    /**
     * 统一异常处理
     * @param joinPoint 连接点
     * @param e 运行时异常
     */
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, RuntimeException e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LogErrorInfo errorInfo = new LogErrorInfo();
        errorInfo.setIp(request.getRemoteAddr());
        errorInfo.setUrl(request.getRequestURL().toString());
        errorInfo.setHttpMethod(request.getMethod());
        errorInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()));
        errorInfo.setRequestParams(getRequestParams(joinPoint));
        errorInfo.setException(e);
        logger.info("Error Request Info      : {}", errorInfo);
    }

    /**
     * 获取入参
     * @param joinPoint 连接点
     * @return 双列集合
     * */
    private Map<String, Object> getRequestParams(JoinPoint joinPoint) {
        Map<String, Object> requestParams = new HashMap<>();

        //参数名
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                //获取文件名
                value = file.getOriginalFilename();
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }
}
