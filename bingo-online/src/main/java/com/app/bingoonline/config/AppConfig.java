package com.app.bingoonline.config;

import com.app.bingoonline.util.LogUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

import static com.app.bingoonline.util.LogContants.*;


@Configuration
public class AppConfig {
    private static final LogUtil logger = new LogUtil();

    @Bean
    public Random random() {
        logger.createLog(CONFIG_CLASS, CONFIG_TYPE, RANDOM_METHOD, RANDOM_METHOD_MSG, null);

        return new Random();
    }
}