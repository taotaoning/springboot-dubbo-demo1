package com.nt.demo.config.filters;

import com.nt.demo.config.wrapper.WrapperServletRequest;
import com.nt.demo.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.core.annotation.Order;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Create by TaoTaoNing
 * 2019/7/3
 **/
@Slf4j
/**
 * order中数字越小，优先级越高
 */
@Order(0)
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter {
    private static final String SESSION_TOKEN = "user_name";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(this.getClass().getSimpleName() + " -- " + "is init ------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(servletRequest.getContentType());
        HttpServletResponse servletResponse1 = (HttpServletResponse) servletResponse;
        servletResponse1.setHeader("ningtao", "test_header");
        WrapperServletRequest wrapperServletRequest = null;
        // 关于请求包装流的使用
        if (servletRequest instanceof HttpServletRequest){

            HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
            wrapperServletRequest = new WrapperServletRequest(servletRequest1);

        }

        BufferedReader br = new BufferedReader(new InputStreamReader(wrapperServletRequest.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            log.info(line);
            sb.append(line);
        }
        log.info(sb.toString());

        filterChain.doFilter(wrapperServletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info(this.getClass().getSimpleName() + " -- " + "is destroy ------");

    }
}
