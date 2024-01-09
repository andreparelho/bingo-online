package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.model.Raffle;
import com.app.bingoonline.model.extds.*;
import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.repository.impl.ContestRepositoryImpl;
import com.app.bingoonline.repository.impl.TicketRepositoryImpl;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.impl.ContestServiceImpl;
import com.app.bingoonline.service.impl.TicketServiceImpl;
import com.app.bingoonline.converter.Converter;
import com.app.bingoonline.shared.TicketShared;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceImplTest {
    private B b;
    private I i;
    private N n;
    private G g;
    private O o;
    private Raffle raffle;
    private ContestService mockContestService;
    private TicketRepository mockTicketRepository;
    private Converter converter;
    private TicketServiceImpl ticketServiceImpl;
    private ContestEntity contestEntity;
    private ContestRepository mockContestRepository;
    private TicketShared ticketShared;

    @BeforeEach
    public void initConfig(){
        this.b = new B();
        this.i = new I();
        this.n = new N();
        this.g = new G();
        this.o = new O();

        this.converter = mock(Converter.class);

        this.mockContestService = mock(ContestServiceImpl.class);
        this.mockTicketRepository = mock(TicketRepositoryImpl.class);
        this.mockContestRepository = mock(ContestRepositoryImpl.class);

        this.contestEntity = new ContestEntity();
        this.contestEntity.setNumber(1);
        this.contestEntity.setId(1L);
        this.contestEntity.setRaffleNumbers("1");
        this.contestEntity.setContestNumber(1001);

        this.raffle = new Raffle(mockContestRepository);

        this.ticketServiceImpl = new TicketServiceImpl(
                this.b, this.i, this.n, this.g, this.o,
                this.raffle,
                this.mockContestService,
                this.mockTicketRepository,
                this.converter
                );

        this.ticketShared = new TicketShared();
    }

    @Test
    @DisplayName("This test when return one ticket with 25 index")
    public void testGenerateTicket() throws JsonProcessingException {
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
    public void testGenerateTicketByContestId() throws Exception {
        int contestId = 1000;
        when(this.mockContestService.findContestById(contestId)).thenReturn(contestId);

        Map<String, Set<Integer>> ticket = ticketServiceImpl.generateTicketByContestId(contestId);
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
    @DisplayName("This test should return ticket with contest created previously")
    public void testGenerateTicketByContestIdWhenContestCreated() throws Exception {
        int contestId = 1000;

        when(this.mockContestService.findContestById(contestId)).thenReturn(contestId);
        Map<String, Set<Integer>> ticket = this.ticketServiceImpl.generateTicketByContestId(contestId);

        assertNotNull(ticket);
    }

    @Test
    @DisplayName("This test should return exception when contest not created previously")
    public void testGenerateTicketByContestIdWhenContestWrong() throws Exception {
        int contestId = 1001;
        assertThrows(Exception.class, () -> this.ticketServiceImpl.generateTicketByContestId(contestId));
    }

    @Test
    @DisplayName("This test should call one time this generateContestNumber method")
    public void testGenerateTicketWhenCallOneTimeGenerateContestNumberMethod() throws JsonProcessingException {
        int expected = 1;

        when(this.mockContestService.generateContestNumber()).thenReturn(expected);
        when(this.mockContestService.createContest(expected)).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();

        verify(this.mockContestService, times(1)).generateContestNumber();
    }

    @Test
    @DisplayName("This test should call one time this createContest method")
    public void testGenerateTicketWhenCallOneTimeCreateContestMethod() throws JsonProcessingException {
        int expected = 1;

        when(this.mockContestService.generateContestNumber()).thenReturn(expected);
        when(this.mockContestService.createContest(anyInt())).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();

        verify(this.mockContestService, times(1)).createContest(anyInt());
    }

    @Test
    @DisplayName("This test should call two times this mapToJson method")
    public void testThisConverterMethodWhenCallGenerateTicketOrGenerateTicketByContestIdMethods() throws Exception {
        int expected = 2;

        when(this.mockContestService.generateContestNumber()).thenReturn(expected);
        when(this.mockContestService.createContest(anyInt())).thenReturn(this.contestEntity);

        this.ticketServiceImpl.generateTicket();
        this.ticketServiceImpl.generateTicketByContestId(anyInt());

        verify(this.converter, times(expected)).mapToJson(anyMap());
    }

    @Test
    public void testGetAllTicketsByContest(){
        when(this.mockTicketRepository.getAllTicketsByContest(anyInt())).thenReturn(anyList());

        Map<Integer, List<TicketEntity>> allTickets = this.ticketServiceImpl.getAllTicketsByContest(this.contestEntity.getContestNumber());

        assertNotNull(allTickets);
        assertInstanceOf(Map.class, allTickets);

        verify(this.mockTicketRepository, times(1)).getAllTicketsByContest(anyInt());
    }
}
