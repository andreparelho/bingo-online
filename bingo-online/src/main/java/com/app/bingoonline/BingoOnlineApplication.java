package com.app.bingoonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.app.bingoonline.repository")
@EntityScan(basePackages = "com.app.bingoonline.entity")
@ComponentScan(basePackages = {"com.app.bingoonline.entity.converter", "com.app.bingoonline"})
public class BingoOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingoOnlineApplication.class, args);
	}
}
