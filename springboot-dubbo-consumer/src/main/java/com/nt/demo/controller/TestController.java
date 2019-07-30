package com.nt.demo.controller;

import com.nt.demo.middle.intf.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Create by TaoTaoNing
 * 2019/7/2
 **/

/**
 * 要想返回页面的话，这里必须是Controller注解。
 */
@Controller
@RequestMapping("/page")
public class TestController {

    @Reference
    private TestService testService;

    /**
     * 因为controller类上标注的是@Controller标签，想要返回json或xml等响应信息，必须
     * 使用@ResponseBody注解
     * @return
     */
    @ResponseBody
    @GetMapping("/test")
    public String getHello(){
        return testService.hello();
    }

    @RequestMapping("/hello")
    public String getLogin(Map params, Model model){
        params.put("hello","index");
        model.addAttribute("hi","from server!");
        return "login";
    }

}
