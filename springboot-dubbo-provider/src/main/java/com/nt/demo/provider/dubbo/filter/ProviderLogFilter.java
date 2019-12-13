package com.nt.demo.provider.dubbo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

/**
 * @Description
 * @ClassName ProviderLogFilter
 * @Author NingTao
 * @date 2019.12.13
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER)
public class ProviderLogFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String traceId = (String)RpcContext.getContext().getAttachment("traceId");
        log.info("生产者设置traceId {}",traceId);

        MDC.put("traceId",traceId);
        return invoker.invoke(invocation);
    }
}
