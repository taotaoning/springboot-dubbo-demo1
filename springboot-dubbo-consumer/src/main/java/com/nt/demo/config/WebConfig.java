package com.nt.demo.config;

import com.nt.demo.config.interceptors.LoginInterceptor;
import com.nt.demo.config.interceptors.RepetRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 配置类，主要配置：拦截器，过滤器，跨域配置等
 * Create by TaoTaoNing
 * 2019/7/3
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private RepetRequestInterceptor repetRequestInterceptor;
    /**
     * 添加拦截器，拦截器通过此方法添加才会生效
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截器可以通过spring的依赖注入
        // registry.addInterceptor(拦截器)
        registry.addInterceptor(loginInterceptor).addPathPatterns("/core/*");
//        registry.addInterceptor(repetRequestInterceptor);
    }

    /**
     * 此方法待研究
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    /**
     * 关于跨域的配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/demo/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .allowedHeaders("x-requested-with","Content-Type")
                .maxAge(3600);
    }


    /**
     * 此方法用啦配置静态资源的：eg：html，css，js
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
