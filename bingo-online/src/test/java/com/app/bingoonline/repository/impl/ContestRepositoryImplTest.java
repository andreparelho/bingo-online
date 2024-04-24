package com.app.bingoonline.repository.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.repository.crud.ContestCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContestRepositoryImplTest {
    private ContestRepositoryImpl contestRepository;
    @Autowired
    private ContestCrudRepository contestCrudRepository;
    private ContestEntity contestEntity;

    @BeforeEach
    public void initSetup(){
        this.contestRepository = new ContestRepositoryImpl(this.contestCrudRepository);

        this.contestEntity = ContestEntity
                .builder()
                .id(1l)
                .number(1001)
                .contestNumber(1001)
                .raffleNumbers("\"{\\\"b\\\":[1,5,8,10,11],\\\"g\\\":[49,52,55,57,58],\\\"i\\\":[17,21,24,25,29],\\\"n\\\":[35,41,42,44],\\\"o\\\":[65,67,72,74,62]}\"")
                .build();
    }

    @Test
    @DisplayName("Deve salvar um contest")
    public void testSaveContest(){
        ContestEntity saveContest = this.contestRepository.saveContest(this.contestEntity);

        Long contestId = this.contestEntity.getId();
        int contestNumber = this.contestEntity.getContestNumber();
        String contestRaffleNumbers = this.contestEntity.getRaffleNumbers();
        int number = this.contestEntity.getNumber();

        boolean isGreaterThanZero = saveContest.getId() > 0;

        assertNotNull(saveContest);
        assertInstanceOf(ContestEntity.class, saveContest);

        assertTrue(isGreaterThanZero);
        assertEquals(contestId, saveContest.getId());

        assertEquals(contestNumber, saveContest.getContestNumber());
        assertEquals(contestRaffleNumbers, saveContest.getRaffleNumbers());
        assertEquals(number, saveContest.getNumber());
    }

    @Test
    @DisplayName("Deve retornar todos os contests salvos, caso tenha dados.")
    public void testGetAllContests(){
        List<ContestEntity> emptyList = new ArrayList<>();
        List<ContestEntity> allContests = this.contestRepository.getAllContests();

        assertEquals(emptyList, allContests);

        ContestEntity contestTwo = ContestEntity
                .builder()
                .id(2l)
                .contestNumber(1002)
                .raffleNumbers("45")
                .build();

        this.contestRepository.saveContest(this.contestEntity);
        this.contestRepository.saveContest(contestTwo);

        allContests = this.contestRepository.getAllContests();
        int sizeList = allContests.size();

        assertNotNull(allContests);
        assertEquals(2, sizeList);
    }

    @Test
    @DisplayName("Deve retornar um contest de acordo com o contestNumber")
    public void testFindContestNumber(){
        this.contestRepository.saveContest(this.contestEntity);

        ContestEntity foundContest = this.contestRepository.findContestNumber(this.contestEntity.getNumber());

        assertNotNull(foundContest);
        assertInstanceOf(ContestEntity.class, foundContest);
        assertEquals(this.contestEntity.getContestNumber(), foundContest.getContestNumber());
    }

    @Test
    @DisplayName("Deve salvar raffle dentro do contest")
    public void testSaveRaffleNumberOnContest(){
        this.contestEntity.setRaffleNumbers(null);
        this.contestRepository.saveContest(this.contestEntity);
        ContestEntity savedContest = this.contestRepository.findContestNumber(this.contestEntity.getNumber());

        assertNull(savedContest.getRaffleNumbers());

        contestEntity.setRaffleNumbers("67");
        savedContest = this.contestRepository.findContestNumber(this.contestEntity.getNumber());
        assertNotNull(savedContest);
        this.contestRepository.saveContest(this.contestEntity);

        assertNotNull(savedContest.getRaffleNumbers());
    }
}