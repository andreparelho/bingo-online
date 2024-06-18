package com.app.bingoonline.service.impl;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.contest.service.impl.ContestServiceImpl;
import com.app.bingoonline.raffle.entity.RaffleEntity;
import com.app.bingoonline.contest.dto.response.CreateContestResponse;
import com.app.bingoonline.contest.repository.ContestRepository;
import com.app.bingoonline.raffle.repository.RaffleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ContestServiceImplTest {
    @InjectMocks
    private ContestServiceImpl contestService;

    @Mock
    private RaffleRepository raffleRepository;
    @Mock
    private ContestRepository contestRepository;
    @Mock
    private Random random;

    private ContestEntity contestEntity;
    private RaffleEntity raffleEntity;

    @BeforeEach
    public void initConfig(){
        MockitoAnnotations.initMocks(this);

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
    }

    @Test
    @DisplayName("This test should return a number contest valid")
    public void testDeveGerarUmNumeroContest(){
        when(this.random.nextInt(1000,9999)).thenReturn(1001);

        int contestNumber = this.contestService.generateContestNumber();
        int notExpected = 1010101010;

        assertNotNull(contestNumber);
        assertNotEquals(notExpected, contestNumber);

        verify(this.random, times(1)).nextInt(1000, 9999);
    }

    @Test
    @DisplayName("This test should return a number valid when contest as created")
    public void testDeveCriarUmContestComNumeroValido(){
        int contestNumber = 1001;

        when(this.contestRepository.saveContest(contestEntity)).thenReturn(contestEntity);

        CreateContestResponse response = this.contestService.createContest();

        assertNotNull(response.contest());
    }

    @Test
    public void testCreateContest(){
        when(this.contestRepository.saveContest(this.contestEntity)).thenReturn(this.contestEntity);
        doNothing().when(this.raffleRepository).saveRaffle(this.raffleEntity);

        CreateContestResponse actual = this.contestService.createContest();

        assertNotNull(actual);
    }

    @Test
    public void testCreateContestWithParameter(){
        when(this.contestRepository.saveContest(this.contestEntity)).thenReturn(this.contestEntity);
        ContestEntity actual = this.contestService.createContest(anyInt());

        assertNotNull(actual);
    }
}
