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

        this.contestEntity = new ContestEntity();
        this.contestEntity.setId(1L);
        this.contestEntity.setContestNumber(1001);
        this.contestEntity.setRaffleNumbers("1,2,3,4,5");
        this.contestEntity.setNumber(1001);
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

        ContestEntity contestTwo = new ContestEntity();
        contestTwo.setId(2L);
        contestTwo.setContestNumber(1002);
        contestTwo.setRaffleNumbers("45");
        contestTwo.setNumber(1002);

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

        ContestEntity foundContest = this.contestRepository.findContestNumber(this.contestEntity.getContestNumber());

        assertNotNull(foundContest);
        assertInstanceOf(ContestEntity.class, foundContest);
        assertEquals(this.contestEntity.getContestNumber(), foundContest.getContestNumber());
    }

    @Test
    @DisplayName("Deve salvar raffle dentro do contest")
    public void testSaveRaffleNumberOnContest(){
        this.contestEntity.setRaffleNumbers(null);
        this.contestRepository.saveContest(this.contestEntity);
        ContestEntity savedContest = this.contestRepository.findContestNumber(this.contestEntity.getContestNumber());

        assertNull(savedContest.getRaffleNumbers());

        contestEntity.setRaffleNumbers("67");
        this.contestRepository.saveContest(this.contestEntity);

        assertNotNull(savedContest.getRaffleNumbers());
    }
}