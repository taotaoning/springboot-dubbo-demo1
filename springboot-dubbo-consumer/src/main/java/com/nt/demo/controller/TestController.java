package com.nt.demo.controller;

import com.asiainfo.checkstand.service.PayApiService;
import com.nt.demo.middle.intf.TestService;
import com.nt.demo.pojo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.sound.sampled.Line;
import java.util.Map;

/**
 * Create by TaoTaoNing
 * 2019/7/2
 **/

/**
 * 要想返回页面的话，这里必须是Controller注解。
 */
@Slf4j
@Controller
@RequestMapping("/page")
public class TestController {

    @Reference
    private TestService testService;

    @Reference
    private PayApiService payApiService;

    /**
     * 因为controller类上标注的是@Controller标签，想要返回json或xml等响应信息，必须
     * 使用@ResponseBody注解
     * @return
     */
    @RequestMapping("/test")
    public String getHello(ModelMap modelMap, @ModelAttribute UserVO userVO){
       log.info(userVO.getUserName());
        modelMap.addAttribute("test","testAdd");
        return "meizu";
    }

    @RequestMapping("/hello")
    public String getLogin(ModelMap model){
        model.addAttribute("hi","from server!");
        return "login";
    }


}
