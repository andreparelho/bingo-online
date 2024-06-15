package com.app.bingoonline.service.impl;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.ticket.dto.response.CreatedTicketResponse;
import com.app.bingoonline.ticket.dto.response.TicketResponse;
import com.app.bingoonline.contest.repository.ContestRepository;
import com.app.bingoonline.raffle.service.impl.RaffleServiceImpl;
import com.app.bingoonline.raffle.repository.RaffleRepository;
import com.app.bingoonline.ticket.factory.impl.*;
import com.app.bingoonline.ticket.repository.TicketRepository;
import com.app.bingoonline.contest.service.ContestService;
import com.app.bingoonline.infrastructure.util.mapper.Mapper;
import com.app.bingoonline.ticket.service.impl.TicketServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TicketServiceImplTest {
    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    @Mock
    private ContestService contestService;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private RaffleRepository raffleRepository;
    @Mock
    private ContestRepository contestRepository;
    @Mock
    private RaffleServiceImpl raffleServiceImpl;
    @Mock
    private Mapper mapper;
    @Mock
    private BFactoryImpl bFactoryImpl;
    @Mock
    private IFactoryImpl iFactoryImpl;
    @Mock
    private NFactoryImpl nFactoryImpl;
    @Mock
    private GFactoryImpl gFactoryImpl;
    @Mock
    private OFactoryImpl oFactoryImpl;

    private ContestEntity contestEntity;

    @BeforeEach
    public void initConfig(){
        this.contestEntity = ContestEntity
                .builder()
                .number(1001)
                .contestNumber(1001)
                .build();
    }

    @Test
    @DisplayName("This test when return one ticket with 25 index")
    public void testDeveRetornarUmIndexDeVinteCincoPosicoes() throws JsonProcessingException {
        int contest = 1000;

        this.contestEntity.setContestNumber(contest);

        when(this.contestService.generateContestNumber()).thenReturn(contest);
        when(this.contestService.createContest(contest)).thenReturn(this.contestEntity);

        CreatedTicketResponse ticket = ticketServiceImpl.generateTicket();
        Iterator<Map.Entry<String, List<Integer>>> iterator = ticket.ticketMap().entrySet().iterator();

        int actual = 0;
        while (iterator.hasNext()) {
            actual += iterator.next().getValue().size();
        }

        int expected = 25;
        int finalActual = actual;

        assertNotNull(ticket);
        assertEquals(expected, actual, () -> finalActual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This test when return one ticket with 25 index when contest exists")
    public void testDeveRetornarUmIndexDeVinteCincoPosicoesCasoContestExistente() throws Exception {
        int contestId = 1000;
        when(this.contestService.findContestById(contestId)).thenReturn(contestId);

        TicketResponse ticketResponse = ticketServiceImpl.generateTicketByContestId(contestId);
        CreatedTicketResponse ticket = ticketServiceImpl.generateTicket();
        Iterator<Map.Entry<String, List<Integer>>> iterator = ticketResponse.ticketMap().entrySet().iterator();

        int actual = 0;
        while (iterator.hasNext()) {
            actual += iterator.next().getValue().size();
        }

        int expected = 25;
        assertNotNull(ticket);

        int finalActual = actual;
        assertEquals(expected, actual, () -> finalActual + " did not equal to " + expected);
    }

    @Test
    @DisplayName("This test shuld return ticket with contest created previously")
    public void testDeveRetornarUmTicketComContestJaCriado() throws Exception {
        int contestId = 1000;

        when(this.contestService.findContestById(contestId)).thenReturn(contestId);
        TicketResponse ticket = this.ticketServiceImpl.generateTicketByContestId(contestId);

        assertNotNull(ticket);
    }

    @Test
    @DisplayName("This test shuld return exception when contest not created previously")
    public void testDeveRetonarUmaExceptionCasoConstestErrado() throws Exception {
        int contestId = 1001;
        assertThrows(Exception.class, () -> this.ticketServiceImpl.generateTicketByContestId(contestId));
    }

    @Test
    @DisplayName("This test shuld call one time this generateContestNumber method")
    public void testDeveChamarUmaVezMetodoGenerateContestNumber() throws JsonProcessingException {
        int expected = 1;

        when(this.contestService.generateContestNumber()).thenReturn(expected);
        when(this.contestService.createContest(expected)).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();

        verify(this.contestService, times(1)).generateContestNumber();
    }

    @Test
    @DisplayName("This test shuld call one time this createContest method")
    public void testDeveChamarUmaVezMetodoCreateContest() throws JsonProcessingException {
        int expected = 1;

        when(this.contestService.generateContestNumber()).thenReturn(expected);
        when(this.contestService.createContest(anyInt())).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();

        verify(this.contestService, times(1)).createContest(anyInt());
    }

    @Test
    @DisplayName("This test shuld call two times this mapToJson method")
    public void testDeveChamarDuasVezesMetodoMapToJson() throws Exception {
        int expected = 2;

        when(this.contestService.generateContestNumber()).thenReturn(expected);
        when(this.contestService.createContest(anyInt())).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();
        this.ticketServiceImpl.generateTicketByContestId(anyInt());

        verify(this.mapper, times(expected)).mapToJson(anyMap());
    }
}
