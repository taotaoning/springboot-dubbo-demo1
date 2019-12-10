package com.nt.demo.provider.mybatis.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description
 * @ClassName AnalyzeSqlInterceptor
 * @Author NingTao
 * @date 2019.12.10
 */
@Component
@Slf4j
@Intercepts({
        @Signature(method = "query",
                type = Executor.class,
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
})

public class AnalyzeSqlInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 获取MappedStatement实例
        MappedStatement statement = (MappedStatement)invocation.getArgs()[0];

        // 获取sql 命令类型
        SqlCommandType sqlCommandType = statement.getSqlCommandType();

        Object parameter = null;
        if (1 < invocation.getArgs().length){
            parameter = invocation.getArgs()[1];
        }

        String name = invocation.getMethod().getName();
        System.out.println("name is = " + name + " ---- sqlCommandType is " + sqlCommandType.name());

        BoundSql sql = statement.getBoundSql(parameter);

        long startTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();

        System.out.println("执行sql："+sql.getSql()+"， 共耗时：{"+(endTime - startTime)+"}mms");

        return result;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
