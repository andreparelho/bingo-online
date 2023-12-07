package com.app.bingoonline.service;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.model.extds.*;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.repository.impl.TicketRepositoryImpl;
import com.app.bingoonline.service.impl.ContestServiceImpl;
import com.app.bingoonline.service.impl.TicketServiceImpl;
import com.app.bingoonline.converter.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
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

    private Converter converter;
    private TicketServiceImpl ticketServiceImpl;

    @BeforeEach
    public void initConfig(){
        this.b = new B();
        this.i = new I();
        this.n = new N();
        this.g = new G();
        this.o = new O();

        this.converter = new Converter();

        this.mockContestService = mock(ContestServiceImpl.class);
        this.mockTicketRepository = mock(TicketRepositoryImpl.class);

        this.ticketServiceImpl = new TicketServiceImpl(
                this.b, this.i, this.n, this.g, this.o,
                this.mockContestService,
                this.mockTicketRepository,
                this.converter
                );
    }

    @Test
    @DisplayName("This test when return one ticket with 25 index")
    public void testDeveRetornarUmIndexDeVinteCincoPosicoes() throws JsonProcessingException {
        int contest = 1000;

        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setContestNumber(contest);

        when(this.mockContestService.generateContestNumber()).thenReturn(contest);
        when(this.mockContestService.createContest(contest)).thenReturn(contestEntity);

        Map<String, Set<Integer>> ticket = ticketServiceImpl.generateTicket();
        Iterator<Map.Entry<String, Set<Integer>>> iterator = ticket.entrySet().iterator();

        int actual = 0;
        while (iterator.hasNext()) {
            actual += iterator.next().getValue().size();
        }

        int expected = 25;
        assertNotNull(ticket);
        assertEquals(expected, actual);
    }
}
