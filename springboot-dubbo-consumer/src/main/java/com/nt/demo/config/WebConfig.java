package com.nt.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类，主要配置：拦截器，过滤器，跨域配置等
 * Create by TaoTaoNing
 * 2019/7/3
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    /**
     * 关于跨域的配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }
}
