package io.npee.springwebadvanced.app.config.proxyfactory.advice;

import io.npee.springwebadvanced.app.v7.OrderControllerV7;
import io.npee.springwebadvanced.app.v7.OrderRepositoryV7;
import io.npee.springwebadvanced.app.v7.OrderServiceV7;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderRepositoryV7 orderRepositoryV7(LogTrace logTrace) {
        OrderRepositoryV7 target = new OrderRepositoryV7();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderRepositoryV7 proxy = (OrderRepositoryV7) proxyFactory.getProxy();
        log.info("target.getClass() {}", target.getClass());
        log.info("proxy.getClass() {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV7 orderServiceV7(LogTrace logTrace) {
        OrderServiceV7 target = new OrderServiceV7(orderRepositoryV7(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderServiceV7 proxy = (OrderServiceV7) proxyFactory.getProxy();
        log.info("target.getClass() {}", target.getClass());
        log.info("proxy.getClass() {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderControllerV7 orderControllerV7(LogTrace logTrace) {
        OrderControllerV7 target = new OrderControllerV7(orderServiceV7(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderControllerV7 proxy = (OrderControllerV7) proxyFactory.getProxy();
        log.info("target.getClass() {}", target.getClass());
        log.info("proxy.getClass() {}", proxy.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace) {

        // Pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        // Advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }


}
