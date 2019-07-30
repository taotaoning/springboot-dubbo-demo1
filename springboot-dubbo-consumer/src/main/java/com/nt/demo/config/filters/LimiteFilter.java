package com.nt.demo.config.filters;

import com.nt.demo.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by TaoTaoNing
 * 2019/7/26
 **/
@Slf4j
@WebFilter(filterName = "limitFilter", urlPatterns = "/core/*")
public class LimiteFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //((HttpServletResponse) servletResponse).setHeader("Locatin","/demo/core/register");

        // 以redis实现限制请求频率
        String remoteAddr = ((HttpServletRequest) servletRequest).getRemoteAddr();

        Jedis redis = RedisUtils.getRedis();
        System.out.println("redis 存在  =  " + redis.exists(remoteAddr));
        if (!redis.exists(remoteAddr)) {
            redis.set(remoteAddr, String.valueOf(10));
            redis.expire(remoteAddr, 20);
        }
        ((HttpServletResponse) servletResponse).setHeader("X-RateLimit-Limit", "10");
        ((HttpServletResponse) servletResponse).setHeader("X-RateLimit-Reset", String.valueOf(redis.ttl(remoteAddr)));
        ((HttpServletResponse) servletResponse).setHeader("X-RateLimit-Remaining", String.valueOf(redis.decr(remoteAddr)));

        Integer i = Integer.valueOf(redis.get(remoteAddr));
        if (i <= 0) {
            //redis.del(remoteAddr);
           // servletRequest.getRequestDispatcher("/meizu.html").forward(servletRequest, servletResponse);
             ((HttpServletResponse) servletResponse).sendRedirect("/demo/meizu.html");
           // ((HttpServletResponse) servletResponse).setHeader("Location","/demo/meizu.html");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
