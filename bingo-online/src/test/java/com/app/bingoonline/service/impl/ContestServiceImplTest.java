package com.app.bingoonline.service.impl;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.contest.service.impl.ContestServiceImpl;
import com.app.bingoonline.raffle.entity.RaffleEntity;
import com.app.bingoonline.contest.dto.response.CreateContestResponse;
import com.app.bingoonline.contest.repository.ContestRepository;
import com.app.bingoonline.raffle.repository.RaffleRepository;
import com.app.bingoonline.contest.repository.impl.ContestRepositoryImpl;
import com.app.bingoonline.raffle.repository.impl.RaffleRepositoryImpl;
import com.app.bingoonline.contest.service.ContestService;
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
    private RaffleRepository mockRaffleRepository;
    private ContestRepository mockContestRepository;
    private ContestEntity contestEntity;
    private RaffleEntity raffleEntity;

    @BeforeEach
    public void initConfig(){
        this.contestEntity = ContestEntity
                .builder()
                .contestNumber(1001)
                .build();

        this.raffleEntity = RaffleEntity.builder()
                .id(1l)
                .raffleNumbers("1, 3, 4")
                .contestId(1001l)
                .raffleSortedNumbers("40")
                .build();

        this.random = new Random();

        this.mockContestRepository = mock(ContestRepositoryImpl.class);
        this.mockContestService = mock(ContestServiceImpl.class);
        this.mockRaffleRepository = mock(RaffleRepositoryImpl.class);

        this.contestService = new ContestServiceImpl(
                this.mockContestRepository,
                mockRaffleRepository,
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
        doNothing().when(this.mockRaffleRepository).saveRaffle(this.raffleEntity);

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
