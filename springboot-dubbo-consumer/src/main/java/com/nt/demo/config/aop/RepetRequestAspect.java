package com.nt.demo.config.aop;

import com.alibaba.fastjson.JSONObject;
import com.asiainfo.checkstand.domain.vo.BusinessParam;
import com.nt.demo.config.limreq.LimiteRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description
 * @ClassName RepetRequestAspect
 * @Author NingTao
 * @date 2020.04.01
 */
@Slf4j
@Component
@Aspect
public class RepetRequestAspect {



    @Around("execution(* com.nt.demo.controller.TestController.*(..)) && @annotation(limiteRequest)")
    public Object repetRequest(ProceedingJoinPoint pjp, LimiteRequest limiteRequest) {

        Object proceed = null;
        try {
            // 获取请求
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();

            String ipAddress = getIPAddress(request);
            Object[] args = pjp.getArgs();
            Object loginTime = null;
            HashMap<String,Object> param = (HashMap<String,Object>)pjp.getArgs()[0];

            Object userName = null;
            for (int i = 0; i < args.length; i++){

                if (args[i] instanceof HashMap){
                    loginTime = ((HashMap)args[0]).get("loginTime");
                    userName = ((HashMap)args[0]).get("userName");
                }
            }

            log.info("提交时间:{}",new SimpleDateFormat("yyMMddHHmmss").format());
            log.info("提交时间:{}",userName);


            proceed = pjp.proceed();

        }catch (Throwable e){
            log.error(e.getMessage());
        }

        return proceed;
    }


    /**
     * 获取ip
     * @param request
     * @return
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}
