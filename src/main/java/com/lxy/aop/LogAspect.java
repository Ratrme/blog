package com.lxy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author lxy
 * @Description Log信息切片
 * @Date 2022/11/20 20:45
 */

@Slf4j
@Aspect
@Component
public class LogAspect {


    @Pointcut("execution(* com.lxy.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String url = request.getRequestURL().toString();
            String ip = request.getRemoteAddr();
            String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
            log.info("Request : {}",requestLog);
        }
    }

    @After("log()")
    public void doAfter() {}

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturning(Object result) {
        log.info("Result:{}", result);
    }

}
