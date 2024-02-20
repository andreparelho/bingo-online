package com.app.bingoonline.model;

import com.app.bingoonline.repository.ContestRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class RaffleTest {
    private Raffle raffle;

    @Mock
    private ContestRepository mockConstestRepository;

    @BeforeEach
    public void init(){
        this.raffle = new Raffle(this.mockConstestRepository);
    }

    @Test
    public void testDeveRetornarUmNumeroAleatorioEntre1e75(){
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(1);

        String raffleNumberString = this.raffle.createRandomRaffleNumber();
        assertNotNull(raffleNumberString);

        int raffleNumberInt = Integer.parseInt(raffleNumberString);

        boolean isValidNumbers = raffleNumberInt >= 1 && raffleNumberInt <= 75;
        assertTrue(isValidNumbers);
    }

}