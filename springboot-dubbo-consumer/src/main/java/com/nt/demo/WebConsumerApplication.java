package com.nt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Create by TaoTaoNing
 * 2019/7/2
 **/
@ServletComponentScan
@SpringBootApplication
public class WebConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebConsumerApplication.class,args);
    }
}
