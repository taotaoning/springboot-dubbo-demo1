package com.nt.demo.dubbo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.UUID;

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
        String s = String.valueOf(UUID.randomUUID());
        RpcContext.getContext().setAttachment("traceId", StringUtils.isBlank(MDC.get("traceId")) ? s : MDC.get("traceId"));
        MDC.put("traceId",s);
        log.info("消费者设置traceId {}",s);
        return invoker.invoke(invocation);
    }
}
