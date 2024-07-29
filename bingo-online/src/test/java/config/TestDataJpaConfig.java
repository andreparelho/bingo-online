package config;

import com.app.bingoonline.contest.repository.crud.ContestCrudRepository;
import com.app.bingoonline.contest.repository.impl.ContestRepositoryImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
@TestConfiguration
public class TestDataJpaConfig {
    @Bean
    public ContestRepositoryImpl contestRepositoryImpl(ContestCrudRepository contestCrudRepository) {
        return new ContestRepositoryImpl(contestCrudRepository);
    }

}
