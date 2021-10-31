package io.npee.springwebadvanced;

import io.npee.springwebadvanced.trace.prod.FieldLogTrace;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
