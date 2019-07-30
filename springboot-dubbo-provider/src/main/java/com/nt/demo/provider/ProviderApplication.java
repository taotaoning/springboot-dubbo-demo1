package com.nt.demo.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create by TaoTaoNing
 * 2019/7/2
 **/

@SpringBootApplication
/**
 * 将项目中对应的mapper类的路径加进来就可以了
 */
@MapperScan(basePackages = "com.nt.demo.provider.dao")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
    }
}
