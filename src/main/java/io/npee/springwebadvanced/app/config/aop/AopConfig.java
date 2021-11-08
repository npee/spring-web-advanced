package io.npee.springwebadvanced.app.config.aop;

import io.npee.springwebadvanced.app.config.aop.aspect.LogTraceAspect;
import io.npee.springwebadvanced.app.config.common.AppV6Config;
import io.npee.springwebadvanced.app.config.common.AppV7Config;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV6Config.class, AppV7Config.class})
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
}
