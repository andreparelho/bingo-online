package com.app.bingoonline.model;

import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.service.impl.RaffleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RaffleServiceImplTest {
    private RaffleServiceImpl raffleServiceImpl;

    @Mock
    private ContestRepository mockConstestRepository;

    @BeforeEach
    public void init(){
        this.raffleServiceImpl = new RaffleServiceImpl(this.mockConstestRepository);
    }

    @Test
    public void testDeveRetornarUmNumeroAleatorioEntre1e75(){
        Random random = mock(Random.class);
        when(random.nextInt()).thenReturn(1);

        String raffleNumberString = this.raffleServiceImpl.createRandomRaffleNumber();
        assertNotNull(raffleNumberString);

        int raffleNumberInt = Integer.parseInt(raffleNumberString);

        boolean isValidNumbers = raffleNumberInt >= 1 && raffleNumberInt <= 75;
        assertTrue(isValidNumbers);
    }

}