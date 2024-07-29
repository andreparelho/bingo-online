package com.app.bingoonline.contest.service.impl;

import com.app.bingoonline.contest.dto.response.ContestResponseList;
import com.app.bingoonline.contest.dto.response.CreateContestResponse;
import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.contest.repository.ContestRepository;
import com.app.bingoonline.raffle.repository.RaffleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ContestServiceImplTest {
    @InjectMocks
    private ContestServiceImpl contestService;

    @Mock
    private RaffleRepository raffleRepository;

    @Mock
    private ContestRepository contestRepository;

    @Mock
    private Random random;

    private ContestEntity contestEntity;

    @BeforeEach
    public void setup(){
        contestEntity = ContestEntity.builder()
                .number(new Random().nextInt(1000, 9999))
                .contestNumber(new Random().nextInt(1000, 9999))
                .build();
    }

    @Test
    @DisplayName("Deve retonar e validar um numero entre 1000 e 9999 com sucesso")
    public void testGenerateContestNumberShouldReturnValidNumber(){
        when(this.random.nextInt(1000, 9999)).thenReturn(new Random().nextInt(1000, 9999));

        var response = this.contestService.generateContestNumber();

        assertNotNull(response);
        assertInstanceOf(Integer.class, response);
        assertTrue(response >= 1000 && response <= 9999);

        verify(this.random, times(1)).nextInt(1000, 9999);
    }

    @Test
    @DisplayName("Deve retonar e validar um numero entre 1000 e 9999 com sucesso")
    public void testGenerateContestNumberShouldReturnValidNumberWhenExistsContestOnDatabase(){
        int number = this.random.nextInt(1000, 9999);

        ContestEntity contest = ContestEntity
                .builder()
                .contestNumber(number)
                .build();

        when(this.random.nextInt(1000, 9999)).thenReturn(number);
        when(this.contestRepository.findContestNumber(number))
                .thenReturn(contest)
                .thenReturn(null);

        var response = this.contestService.generateContestNumber();

        assertNotNull(response);
        assertInstanceOf(Integer.class, response);

        verify(this.contestRepository, times(2)).findContestNumber(number);
    }

    @Test
    @DisplayName("Deve retonar e validar um contest criado com sucesso")
    public void testCreateContestWithParamShouldReturnContest(){
        when(this.contestRepository.saveContest(any())).thenReturn(any());

        var response = this.contestService.createContest(new Random().nextInt(1000, 9999));

        assertNotNull(response);
        assertInstanceOf(ContestEntity.class, response);

        verify(this.contestRepository, times(1)).saveContest(any());
    }

    @Test
    @DisplayName("Deve retonar e validar um contest criado com sucesso")
    public void testCreateContestWithoutParamShouldReturnContest(){
        var response = this.contestService.createContest();

        assertInstanceOf(CreateContestResponse.class, response);
        assertInstanceOf(HashMap.class, response.contest());

        verify(this.contestRepository, times(1)).saveContest(any());
        verify(this.raffleRepository, times(1)).saveRaffle(any());
    }

    @Test
    @DisplayName("Deve retonar e validar um numero criado pelo metodo")
    public void testFindContestByIdShouldReturnANumber(){
        int contestNumber = new Random().nextInt(1000, 9999);

        when(contestRepository.findContestNumber(contestNumber)).thenReturn(contestEntity);

        int response = contestService.findContestById(contestNumber);

        assertNotNull(response);
        assertInstanceOf(Integer.class, response);

        verify(this.contestRepository, times(1)).findContestNumber(contestNumber);
    }

    @Test
    @DisplayName("Deve retonar e validar uma lista de contests criados")
    public void testGetAllContestsShouldReturnListContest(){
        when(this.contestRepository.getAllContests()).thenReturn(getContestList());

        var response = this.contestService.getAllContests();

        assertNotNull(response);
        assertInstanceOf(ContestResponseList.class, response);

        verify(this.contestRepository, times(1)).getAllContests();
    }

    @Test
    @DisplayName("Deve retornar um contest quando chamar a repository")
    public void testFindContestShouldReturnContest(){
        when(this.contestRepository.findContestNumber(contestEntity.getContestNumber())).thenReturn(this.contestEntity);

        var response = this.contestService.findContest(contestEntity.getContestNumber());

        assertNotNull(response);
        assertInstanceOf(ContestEntity.class, response);

        verify(this.contestRepository, times(1)).findContestNumber(contestEntity.getContestNumber());
    }

    @Test
    @DisplayName("Deve retornar um contest quando chamar a repository")
    public void testGetAllRaffleNumbersShouldReturnContest(){
        when(this.contestRepository.findContestNumber(contestEntity.getContestNumber())).thenReturn(this.contestEntity);

        var response = this.contestService.getAllRaffleNumbers(contestEntity.getContestNumber());

        assertNotNull(response);
        assertInstanceOf(ContestEntity.class, response);

        verify(this.contestRepository, times(1)).findContestNumber(contestEntity.getContestNumber());
    }

    @Test
    @DisplayName("Deve salvar um contest quando chamar a repository")
    public void testSaveRaffleNumberShouldReturnContest(){
        when(this.contestRepository.saveRaffleNumberOnContest(contestEntity.getContestNumber(), "65")).thenReturn(this.contestEntity);

        var response = this.contestService.saveRaffleNumber(contestEntity.getContestNumber(), "65");

        assertNotNull(response);
        assertInstanceOf(ContestEntity.class, response);

        verify(this.contestRepository, times(1)).saveRaffleNumberOnContest(contestEntity.getContestNumber(), "65");
    }

    private List<ContestEntity> getContestList(){
        List<ContestEntity> contestList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ContestEntity contest = ContestEntity.builder()
                    .number(new Random().nextInt(1000, 9999))
                    .contestNumber(new Random().nextInt(1000, 9999))
                    .build();

            contestList.add(contest);
        }

        return contestList;
    }
}