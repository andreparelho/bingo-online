package com.app.bingoonline;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
class BingoOnlineApplicationTests {
	@Value("classpath:mappings/test.json")
	private Resource resource;

	@Test
	void contextLoads() {
		System.out.println(resource);
	}

}
