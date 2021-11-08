package io.npee.springwebadvanced.app.config.common;

import io.npee.springwebadvanced.app.v6.*;
import io.npee.springwebadvanced.app.v7.OrderControllerV7;
import io.npee.springwebadvanced.app.v7.OrderRepositoryV7;
import io.npee.springwebadvanced.app.v7.OrderServiceV7;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV7Config {

    @Bean
    public OrderControllerV7 orderControllerV7() {
        return new OrderControllerV7(orderServiceV7());
    }

    @Bean
    public OrderServiceV7 orderServiceV7() {
        return new OrderServiceV7(orderRepositoryV7());
    }

    @Bean
    public OrderRepositoryV7 orderRepositoryV7() {
        return new OrderRepositoryV7();
    }
}
