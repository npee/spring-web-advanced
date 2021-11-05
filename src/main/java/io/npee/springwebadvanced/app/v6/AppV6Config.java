package io.npee.springwebadvanced.app.v6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV6Config {
    
    @Bean
    public OrderControllerV6 orderControllerV6() {
        return new OrderControllerV6Impl(orderServiceV6());
    }

    @Bean
    public OrderServiceV6 orderServiceV6() {
        return new OrderServiceV6Impl(orderRepositoryV6());
    }

    @Bean
    public OrderRepositoryV6 orderRepositoryV6() {
        return new OrderRepositoryV6Impl();
    }
}
