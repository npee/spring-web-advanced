package io.npee.springwebadvanced.app.config.dynamicproxy;

import io.npee.springwebadvanced.app.v6.*;
import io.npee.springwebadvanced.app.config.dynamicproxy.handler.LogTraceBasicHandler;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    @Bean
    public OrderRepositoryV6 orderRepositoryV6(LogTrace logTrace) {
        OrderRepositoryV6 repository = new OrderRepositoryV6Impl();
        OrderRepositoryV6 proxy = (OrderRepositoryV6) Proxy.newProxyInstance(OrderRepositoryV6.class.getClassLoader(), new Class[]{OrderRepositoryV6.class}, new LogTraceBasicHandler(repository, logTrace));
        return proxy;
    }

    @Bean
    public OrderServiceV6 orderServiceV6(LogTrace logTrace) {
        OrderServiceV6 service = new OrderServiceV6Impl(orderRepositoryV6(logTrace));
        OrderServiceV6 proxy = (OrderServiceV6) Proxy.newProxyInstance(OrderServiceV6.class.getClassLoader(), new Class[]{OrderServiceV6.class}, new LogTraceBasicHandler(service, logTrace));
        return proxy;
    }

    @Bean
    public OrderControllerV6 orderControllerV6(LogTrace logTrace) {
        OrderControllerV6 controller = new OrderControllerV6Impl(orderServiceV6(logTrace));
        OrderControllerV6 proxy = (OrderControllerV6) Proxy.newProxyInstance(OrderControllerV6.class.getClassLoader(), new Class[]{OrderControllerV6.class}, new LogTraceBasicHandler(controller, logTrace));
        return proxy;

    }
}
