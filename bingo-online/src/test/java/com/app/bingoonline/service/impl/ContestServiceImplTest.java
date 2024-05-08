package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.model.response.CreateContestResponse;
import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.impl.ContestRepositoryImpl;
import com.app.bingoonline.service.ContestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContestServiceImplTest {
    private Random random;
    private ContestService contestService;
    private ContestService mockContestService;
    private ContestRepository mockContestRepository;
    private ContestEntity contestEntity;

    @BeforeEach
    public void initConfig(){
        this.contestEntity = ContestEntity
                .builder()
                .contestNumber(1001)
                .raffleNumbers("\"{\\\"b\\\":[1,5,8,10,11],\\\"g\\\":[49,52,55,57,58],\\\"i\\\":[17,21,24,25,29],\\\"n\\\":[35,41,42,44],\\\"o\\\":[65,67,72,74,62]}\"")
                .build();

        this.random = new Random();

        this.mockContestRepository = mock(ContestRepositoryImpl.class);
        this.mockContestService = mock(ContestServiceImpl.class);

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
        int contestNumber = 1001;

        when(this.mockContestRepository.saveContest(contestEntity)).thenReturn(contestEntity);

        assertNotNull(contestEntity);
        assertEquals(contestEntity.getContestNumber(), contestNumber);
    }

    @Test
    public void testCreateContest(){
        when(this.mockContestRepository.saveContest(this.contestEntity)).thenReturn(this.contestEntity);
        CreateContestResponse actual = this.contestService.createContest();

        assertNotNull(actual);
        verify(this.mockContestRepository, times(1)).saveContest(any(ContestEntity.class));
    }

    @Test
    public void testCreateContestWithParameter(){
        when(this.mockContestRepository.saveContest(this.contestEntity)).thenReturn(this.contestEntity);
        ContestEntity actual = this.contestService.createContest(anyInt());

        assertNotNull(actual);
        verify(this.mockContestRepository, times(1)).saveContest(any(ContestEntity.class));
    }
}
