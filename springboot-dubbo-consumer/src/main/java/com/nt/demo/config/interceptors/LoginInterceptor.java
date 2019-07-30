package com.nt.demo.config.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Create by TaoTaoNing
 * 2019/7/30
 **/
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public static final String NO_INTERCEPTOR_PATH = ".*/((.css)|(.js)|(images)|(login)|(anon)).*";

    /**
     * 这个方法是在访问接口之前执行的
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果映射到的不是方法，放通
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 方法注解级拦截器
        HandlerMethod handler1 = (HandlerMethod) handler;
        // 可以根据方法名来判断是否拦截
        Method method = handler1.getMethod();
        String name = method.getName();
        if (name.equals("getUser")) {
            // 这里写拦截的逻辑，
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
