package com.app.bingoonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.app.bingoonline.contest.repository", "com.app.bingoonline.infrastructure.db",
		"com.app.bingoonline.raffle.repository", "com.app.bingoonline.ticket.repository",
		"com.app.bingoonline.user.repository"
})
@EntityScan(basePackages = {
		"com.app.bingoonline.contest.entity", "com.app.bingoonline.raffle.entity",
		"com.app.bingoonline.ticket.entity", "com.app.bingoonline.user.entity"
})
@ComponentScan(basePackages = {
		"com.app.bingoonline.infrastructure.util.mapper.Mapper", "com.app.bingoonline"
})
public class BingoOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingoOnlineApplication.class, args);
	}
}
