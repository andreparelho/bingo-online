package com.app.bingoonline.contest.repository.impl;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.contest.repository.crud.ContestCrudRepository;
import config.TestDataJpaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestDataJpaConfig.class)
public class ContestRepositoryImplTest {
    @Autowired
    private ContestRepositoryImpl contestRepository;

    @Autowired
    private ContestCrudRepository contestCrudRepository;

    private ContestEntity contestEntity;

    @BeforeEach
    public void setup()  {
        contestEntity = ContestEntity.builder()
                .number(new Random().nextInt(1000, 9999))
                .contestNumber(new Random().nextInt(1000, 9999))
                .build();
    }

    @Test
    @DisplayName("Deve salvar com sucesso um contest")
    public void testSaveContestShouldReturnContestSavedWithSuccess() {
        var response = this.contestRepository.saveContest(contestEntity);

        assertNotNull(response);
        assertInstanceOf(ContestEntity.class, response);
        assertInstanceOf(Integer.class, response.getContestNumber());
        assertInstanceOf(Integer.class, response.getNumber());
    }

    @Test
    @DisplayName("Deve buscar todos os contest com sucesso")
    public void testGetAllContestShouldReturnAllContestsWithSuccess() {
        this.saveContests();
        var response = this.contestRepository.getAllContests();

        assertNotNull(response);
        assertInstanceOf(List.class, response);
    }

    @Test
    @DisplayName("Deve buscar um contest pelo numero com sucesso")
    public void testFindContestNumberShouldReturnValidContest(){
        this.contestRepository.saveContest(contestEntity);
        var response = this.contestRepository.findContestNumber(contestEntity.getNumber());

        assertNotNull(response);
        assertInstanceOf(ContestEntity.class, response);
    }

    private void saveContests(){
        for (int i = 0; i < 15; i++) {
            contestEntity = ContestEntity.builder()
                    .number(new Random().nextInt(1000, 9999))
                    .contestNumber(new Random().nextInt(1000, 9999))
                    .build();

            this.contestRepository.saveContest(contestEntity);
        }
    }


}