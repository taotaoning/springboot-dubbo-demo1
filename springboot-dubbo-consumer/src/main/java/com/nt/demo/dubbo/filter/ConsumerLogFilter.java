package com.nt.demo.dubbo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

/**
 * @Description
 * @ClassName ConsumerLogFilter
 * @Author NingTao
 * @date 2019.12.13
 */
@Slf4j
@Activate(group = CommonConstants.CONSUMER)
public class ConsumerLogFilter  implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 获取上下文信息  并设置traceId
        RpcContext.getContext().setAttachment("traceId", MDC.get("traceId"));
        log.info("消费者设置traceId {}",MDC.get("traceId"));
        return invoker.invoke(invocation);
    }
}
