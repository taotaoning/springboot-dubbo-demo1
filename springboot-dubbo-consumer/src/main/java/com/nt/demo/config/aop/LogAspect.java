package com.nt.demo.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
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
    public void executeMethod() {
    }

    /**
     * 前置通知，方法调用前调用
     *
     * @param joinPoint
     */
    @Before("executeMethod()")
    public void beforeDoController(JoinPoint joinPoint) {
        log.info("前置通知---beforeDoController");
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
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("请求URI = " + request.getRequestURI());
        log.info("请求ip = " + request.getRemoteHost());
        log.info("请求URL = " + request.getRequestURL());
        log.info("请求args = " + Arrays.toString(joinPoint.getArgs()));
        log.info("请求方法 = " + joinPoint.getSignature().getDeclaringTypeName() + "_" + joinPoint.getSignature().getName());

    }

    /**
     * 后置通知，方法调用后调用
     *
     * @param joinPoint
     */
    @After("executeMethod()")
    public void afterDoController(JoinPoint joinPoint) {
        log.info("后置通知---afterDoController");
        log.info("after = " + joinPoint.toLongString());
    }


    /**
     * 环绕通知：
     * 可以决定方法是否调用，执行时替换方法参数，执行完毕是否扩展返回值
     * 第一个参数必须是ProceedingJoinPoint类型
     *
     * @param proceedingJoinPoint
     */
    @Around("executeMethod()")
    public Object aroundDoController(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("环绕通知------aroundDoController");
        Object[] args = proceedingJoinPoint.getArgs();
        Object proceed = null;
        try {
            // 执行目标方法
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return proceed;
    }


    /**
     * 后置异常通知:
     * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     * throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     *
     * @param joinPoint
     * @param throwable
     */
    @AfterThrowing(value = "executeMethod()", throwing = "throwable")
    public void exceptionAspect(JoinPoint joinPoint, Throwable throwable) {
        log.info("异常通知------exceptionAspect");

        log.info(joinPoint.getSignature().getName());

        if (throwable instanceof NullPointerException) {
            log.info("空指针异常！");
        }

    }


    /**
     * 后置返回通知
     * 这里需要注意的是:
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value = "execution(* com.nt.demo.controller..*.*(..))",returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object keys) {
        System.out.println("后置返回通知------doAfterReturningAdvice1");

        System.out.println("后置返回通知的返回值：" + keys);
    }
}
