package io.npee.springwebadvanced.app.v7.proxy;

import io.npee.springwebadvanced.app.v7.OrderControllerV7;
import io.npee.springwebadvanced.app.v7.OrderRepositoryV7;
import io.npee.springwebadvanced.app.v7.OrderServiceV7;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV7 orderControllerV7(LogTrace logTrace) {
        OrderControllerV7 controller = new OrderControllerV7(orderServiceV7(logTrace));
        return new OrderControllerConcreteProxy(controller, logTrace);
    }

    @Bean
    public OrderServiceV7 orderServiceV7(LogTrace logTrace) {
        OrderServiceV7 service = new OrderServiceV7(orderRepositoryV7(logTrace));
        return new OrderServiceConcreteProxy(service, logTrace);
    }

    @Bean
    public OrderRepositoryV7 orderRepositoryV7(LogTrace logTrace) {
        OrderRepositoryV7 repository = new OrderRepositoryV7();
        return new OrderRepositoryConcreteProxy(repository, logTrace);
    }
}
