package com.app.bingoonline.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

@SpringBootTest
public class IntegrationTest {
    @Value("classpath:mappings/test.json")
    private Resource resource;

    @Test
    void test(){
        System.out.println(resource);
    }
}
