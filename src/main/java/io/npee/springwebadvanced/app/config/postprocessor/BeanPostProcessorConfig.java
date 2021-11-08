package io.npee.springwebadvanced.app.config.postprocessor;

import io.npee.springwebadvanced.app.config.common.AppV6Config;
import io.npee.springwebadvanced.app.config.postprocessor.processor.PackageLogTracePostProcessor;
import io.npee.springwebadvanced.app.config.proxyfactory.advice.LogTraceAdvice;
import io.npee.springwebadvanced.app.config.common.AppV7Config;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV6Config.class, AppV7Config.class})
public class BeanPostProcessorConfig  {

    private final String[] PACKAGE_NAMES = {
            "io.npee.springwebadvanced.app.v6",
            "io.npee.springwebadvanced.app.v7",
            "io.npee.springwebadvanced.app.v8"
    };

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace logTrace) {
        return new PackageLogTracePostProcessor(PACKAGE_NAMES, getAdvisor(logTrace));
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
