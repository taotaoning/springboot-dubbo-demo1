package com.nt.demo.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Create by TaoTaoNing
 * 2019/7/30
 **/
@Slf4j
@Component
@Aspect
public class LogAspect {

    @Pointcut("execution(* com.nt.demo.controller..*.*(..))")
    public void executeMethod(){}

    @Before("executeMethod()")
    public void beforeDoController(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        log.info(Arrays.toString(args));

        String kind = joinPoint.getKind();
        log.info(kind);

        Signature signature = joinPoint.getSignature();
        log.info(signature.getName());

        SourceLocation sourceLocation = joinPoint.getSourceLocation();
     //   log.info(sourceLocation.getFileName());

        Object target = joinPoint.getTarget();

        log.info(target.toString());

        // 获取请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("请求URI = " + request.getRequestURI());
        log.info("请求ip = " + request.getRemoteHost());
        log.info("请求URL = " + request.getRequestURL());
        log.info("请求args = " + Arrays.toString(joinPoint.getArgs()));
        log.info("请求方法 = " + joinPoint.getSignature().getDeclaringTypeName() + "_" + joinPoint.getSignature().getName());

    }

    @After("executeMethod()")
    public void afterDoController(JoinPoint joinPoint){
        log.info("after = " + joinPoint.toLongString());
    }


}
