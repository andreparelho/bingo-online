package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.model.*;
import com.app.bingoonline.controller.response.TicketResponse;
import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.repository.impl.TicketRepositoryImpl;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.mapper.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceImplTest {
    private B b;
    private I i;
    private N n;
    private G g;
    private O o;
    private ContestService mockContestService;
    private TicketRepository mockTicketRepository;
    private Mapper mapper;
    private TicketServiceImpl ticketServiceImpl;
    private ContestEntity contestEntity;
    @Mock
    private RaffleRepository mockRaffleRepository;
    @Mock
    private ContestRepository mockConstestRepository;
    private RaffleServiceImpl raffleServiceImpl;


    @BeforeEach
    public void initConfig(){
        this.b = new B();
        this.i = new I();
        this.n = new N();
        this.g = new G();
        this.o = new O();

        this.mockContestService = mock(ContestServiceImpl.class);
        this.mockTicketRepository = mock(TicketRepositoryImpl.class);
        this.raffleServiceImpl = new RaffleServiceImpl(mockRaffleRepository, mapper, new Random(), this.ticketServiceImpl, this.mockContestService);

        this.mapper = mock(Mapper.class);


        this.contestEntity = ContestEntity
                .builder()
                .number(1001)
                .contestNumber(1001)
                .build();

        this.ticketServiceImpl = new TicketServiceImpl(
                this.b, this.i, this.n, this.g, this.o,
                this.raffleServiceImpl,
                this.mockContestService,
                this.mockTicketRepository,
                this.mapper
                );
    }

    @Test
    @DisplayName("This test when return one ticket with 25 index")
    public void testDeveRetornarUmIndexDeVinteCincoPosicoes() throws JsonProcessingException {
        int contest = 1000;

        this.contestEntity.setContestNumber(contest);

        when(this.mockContestService.generateContestNumber()).thenReturn(contest);
        when(this.mockContestService.createContest(contest)).thenReturn(this.contestEntity);

        Map<String, Set<Integer>> ticket = ticketServiceImpl.generateTicket();
        Iterator<Map.Entry<String, Set<Integer>>> iterator = ticket.entrySet().iterator();

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
    @DisplayName("This test when return one ticket with 25 index when contest exists")
    public void testDeveRetornarUmIndexDeVinteCincoPosicoesCasoContestExistente() throws Exception {
        int contestId = 1000;
        when(this.mockContestService.findContestById(contestId)).thenReturn(contestId);

        TicketResponse ticketResponse = ticketServiceImpl.generateTicketByContestId(contestId);
        Map<String, Set<Integer>> ticket = ticketResponse.ticket();
        Iterator<Map.Entry<String, Set<Integer>>> iterator = ticket.entrySet().iterator();

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

        when(this.mockContestService.findContestById(contestId)).thenReturn(contestId);
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

        when(this.mockContestService.generateContestNumber()).thenReturn(expected);
        when(this.mockContestService.createContest(expected)).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();

        verify(this.mockContestService, times(1)).generateContestNumber();
    }

    @Test
    @DisplayName("This test shuld call one time this createContest method")
    public void testDeveChamarUmaVezMetodoCreateContest() throws JsonProcessingException {
        int expected = 1;

        when(this.mockContestService.generateContestNumber()).thenReturn(expected);
        when(this.mockContestService.createContest(anyInt())).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();

        verify(this.mockContestService, times(1)).createContest(anyInt());
    }

    @Test
    @DisplayName("This test shuld call two times this mapToJson method")
    public void testDeveChamarDuasVezesMetodoMapToJson() throws Exception {
        int expected = 2;

        when(this.mockContestService.generateContestNumber()).thenReturn(expected);
        when(this.mockContestService.createContest(anyInt())).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();
        this.ticketServiceImpl.generateTicketByContestId(anyInt());

        verify(this.mapper, times(expected)).mapToJson(anyMap());
    }
}
