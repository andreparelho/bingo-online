package com.app.bingoonline.service;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.impl.ContestRepositoryImpl;
import com.app.bingoonline.service.impl.ContestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContestServiceImplTest {
    private Random random;
    private ContestService contestService;
    private ContestRepository mockContestRepository;

    @BeforeEach
    public void initConfig(){
        this.random = new Random();
        this.mockContestRepository = mock(ContestRepositoryImpl.class);
        this.contestService = new ContestServiceImpl(
                this.mockContestRepository,
                this.random
        );
    }

    @Test
    @DisplayName("This test should return a number contest valid")
    public void testDeveGerarUmNumeroContest(){
        int contestNumber = this.contestService.generateContestNumber();
        assertNotNull(contestNumber);

        int notExpected = 1010101010;
        assertNotEquals(notExpected, contestNumber);
    }

    @Test
    @DisplayName("This test should return a number valid when contest as created")
    public void testDeveCriarUmContestComNumeroValido(){
        ContestEntity contestEntity = new ContestEntity();
        int contestNumber = 1001;
        contestEntity.setContestNumber(contestNumber);

        when(this.mockContestRepository.saveContest(contestEntity)).thenReturn(contestEntity);

        assertNotNull(contestEntity);
        assertEquals(contestEntity.getContestNumber(), contestNumber);
    }



}
