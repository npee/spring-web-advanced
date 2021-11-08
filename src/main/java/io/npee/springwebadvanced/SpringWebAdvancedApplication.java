package io.npee.springwebadvanced;

import io.npee.springwebadvanced.app.config.autoproxy.AutoProxyConfig;
import io.npee.springwebadvanced.app.config.postprocessor.BeanPostProcessorConfig;
import io.npee.springwebadvanced.trace.prod.LogTrace;
import io.npee.springwebadvanced.trace.prod.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

// @Import({AppV6Config.class, AppV7Config.class})
// @Import(InterfaceProxyConfig.class)
// @Import(DynamicProxyFilterConfig.class)
// @Import(ProxyFactoryConfigV2.class)
// @Import(BeanPostProcessorConfig.class)
@Import(AutoProxyConfig.class)
@SpringBootApplication(
        scanBasePackages = {
            "io.npee.springwebadvanced.app.v6",
            "io.npee.springwebadvanced.app.v7",
            "io.npee.springwebadvanced.app.v8"
})
public class SpringWebAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebAdvancedApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

}
