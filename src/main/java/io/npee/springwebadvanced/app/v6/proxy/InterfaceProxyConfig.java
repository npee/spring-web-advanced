package io.npee.springwebadvanced.app.v6.proxy;

import io.npee.springwebadvanced.app.v6.*;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV6 orderControllerV6(LogTrace logTrace) {
        OrderControllerV6Impl controller = new OrderControllerV6Impl(orderServiceV6(logTrace));
        return new OrderControllerInterfaceProxy(controller, logTrace);
    }

    @Bean
    public OrderServiceV6 orderServiceV6(LogTrace logTrace) {
        OrderServiceV6Impl service = new OrderServiceV6Impl(orderRepositoryV6(logTrace));
        return new OrderServiceInterfaceProxy(service, logTrace);
    }

    @Bean
    public OrderRepositoryV6 orderRepositoryV6(LogTrace logTrace) {
        OrderRepositoryV6Impl repository = new OrderRepositoryV6Impl();
        return new OrderRepositoryInterfaceProxy(repository, logTrace);
    }
}
