package io.npee.springwebadvanced.app.config.proxyfactory.advice;

import io.npee.springwebadvanced.app.v6.*;
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
public class ProxyFactoryConfigV1 {

    @Bean
    public OrderRepositoryV6 orderRepositoryV6(LogTrace logTrace) {
        OrderRepositoryV6 target = new OrderRepositoryV6Impl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderRepositoryV6 proxy = (OrderRepositoryV6) proxyFactory.getProxy();
        log.info("target.getClass() {}", target.getClass());
        log.info("proxy.getClass() {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV6 orderServiceV6(LogTrace logTrace) {
        OrderServiceV6 target = new OrderServiceV6Impl(orderRepositoryV6(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderServiceV6 proxy = (OrderServiceV6) proxyFactory.getProxy();
        log.info("target.getClass() {}", target.getClass());
        log.info("proxy.getClass() {}", proxy.getClass());
        return proxy;
    }

    @Bean
    public OrderControllerV6 orderControllerV6(LogTrace logTrace) {
        OrderControllerV6 target = new OrderControllerV6Impl(orderServiceV6(logTrace));
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(getAdvisor(logTrace));
        OrderControllerV6 proxy = (OrderControllerV6) proxyFactory.getProxy();
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
