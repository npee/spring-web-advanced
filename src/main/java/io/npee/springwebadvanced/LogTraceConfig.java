package io.npee.springwebadvanced;

import io.npee.springwebadvanced.trace.prod.LogTrace;
import io.npee.springwebadvanced.trace.prod.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
