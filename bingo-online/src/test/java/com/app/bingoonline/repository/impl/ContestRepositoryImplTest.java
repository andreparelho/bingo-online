package com.app.bingoonline.repository.impl;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.contest.repository.crud.ContestCrudRepository;
import com.app.bingoonline.contest.repository.impl.ContestRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                .number(1001)
                .contestNumber(1001)
                .build();
    }

    @Test
    @DisplayName("Deve salvar um contest")
    public void testSaveContest(){
        ContestEntity saveContest = this.contestRepository.saveContest(this.contestEntity);

        UUID contestId = this.contestEntity.getId();
        int contestNumber = this.contestEntity.getContestNumber();
        int number = this.contestEntity.getNumber();

        assertNotNull(saveContest);
        assertInstanceOf(ContestEntity.class, saveContest);

        assertEquals(contestId, saveContest.getId());

        assertEquals(contestNumber, saveContest.getContestNumber());
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
                .contestNumber(1002)
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
        this.contestRepository.saveContest(this.contestEntity);
        ContestEntity savedContest = this.contestRepository.findContestNumber(this.contestEntity.getNumber());


        savedContest = this.contestRepository.findContestNumber(this.contestEntity.getNumber());
        assertNotNull(savedContest);
        this.contestRepository.saveContest(this.contestEntity);


    }
}
