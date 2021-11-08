package io.npee.springwebadvanced.app.config.autoproxy;

import io.npee.springwebadvanced.app.config.common.AppV6Config;
import io.npee.springwebadvanced.app.config.common.AppV7Config;
import io.npee.springwebadvanced.app.config.proxyfactory.advice.LogTraceAdvice;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV6Config.class, AppV7Config.class})
public class AutoProxyConfig {

    // @Bean
    public Advisor advisor1(LogTrace logTrace) {
        // Pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        // Advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    // @Bean
    public Advisor advisor2(LogTrace logTrace) {
        // Pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* io.npee.springwebadvanced.app.v*..*(..))");

        // Advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    public Advisor advisor3(LogTrace logTrace) {
        // Pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* io.npee.springwebadvanced.app.v*..*(..)) " +
                "&& !execution(* io.npee.springwebadvanced.app..noLog(..))");

        // Advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
