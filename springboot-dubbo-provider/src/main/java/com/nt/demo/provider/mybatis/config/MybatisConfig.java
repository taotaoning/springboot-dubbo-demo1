package com.nt.demo.provider.mybatis.config;

import com.nt.demo.provider.mybatis.interceptors.AnalyzeSqlInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description
 * @ClassName MybatisConfig
 * @Author NingTao
 * @date 2019.12.10
 */
//@Configuration
public class MybatisConfig {

//    @Autowired
//    private AnalyzeSqlInterceptor interceptor;

//    @Bean
//    public String interceptorMybatis(SqlSessionFactory sqlSessionFactory){
//        sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
//        return "sql";
//    }
}
