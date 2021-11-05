package io.npee.springwebadvanced;

import io.npee.springwebadvanced.app.v6.AppV6Config;
import io.npee.springwebadvanced.app.v7.AppV7Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AppV6Config.class, AppV7Config.class})
@SpringBootApplication(scanBasePackages = {
        "io.npee.springwebadvanced.app.v6",
        "io.npee.springwebadvanced.app.v7",
        "io.npee.springwebadvanced.app.v8"
})
public class SpringWebAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebAdvancedApplication.class, args);
    }

}
