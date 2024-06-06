package com.app.bingoonline.infrastructure.config;

import com.app.bingoonline.infrastructure.util.LogUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

import static com.app.bingoonline.infrastructure.util.LogContants.*;


@Configuration
public class AppConfig {
    private static final LogUtil logger = new LogUtil();

    @Bean
    public Random random() {
        logger.createLog(CONFIG_CLASS, CONFIG_TYPE, RANDOM_METHOD, RANDOM_METHOD_MSG, null);

        return new Random();
    }
}