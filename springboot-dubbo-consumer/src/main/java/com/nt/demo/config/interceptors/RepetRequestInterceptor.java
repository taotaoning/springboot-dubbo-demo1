package com.nt.demo.config.interceptors;

import com.nt.demo.config.limreq.LimiteRequest;
import com.nt.demo.config.wrapper.WrapperServletRequest;
import com.nt.demo.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @Description
 * @ClassName RepetRequestInterceptor
 * @Author NingTao
 * @date 2020.04.01
 */
@Component
@Slf4j
public class RepetRequestInterceptor extends HandlerInterceptorAdapter {

    private static String REPET_VALUE = "repet_request_value";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果映射到的不是方法，放通
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 方法注解级拦截器
        HandlerMethod handler1 = (HandlerMethod) handler;
        LimiteRequest methodAnnotation = handler1.getMethodAnnotation(LimiteRequest.class);
        if (null == methodAnnotation){
            return true;
        }

        WrapperServletRequest servletRequest = new WrapperServletRequest(request);


        log.info("body : ");

        String ipAddress = getIPAddress(request);
        if (StringUtils.isBlank(RedisUtils.getRedis().get(ipAddress))){
            String redisParam = RedisUtils.getRedis().get(ipAddress);
            log.info("缓存信息：" + redisParam);
            if (redisParam.equals("l")){
                render(response);
                return false;
            }
            return true;
        }else {
            RedisUtils.getRedis().set(ipAddress,"hello");
            return true;
        }


    }


    private void render(HttpServletResponse response)throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write("{error}".getBytes("UTF-8"));
        out.flush();
        out.close();
    }


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
