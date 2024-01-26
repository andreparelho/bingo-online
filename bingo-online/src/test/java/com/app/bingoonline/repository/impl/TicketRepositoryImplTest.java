package com.app.bingoonline.repository.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.repository.crud.TicketCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TicketRepositoryImplTest {
    @Autowired
    private TicketCrudRepository mockTicketCrudRepository;
    @InjectMocks
    private TicketRepositoryImpl ticketRepository;
    private TicketEntity ticketEntity;
    private ContestEntity contestEntity;

    @BeforeEach
    public void initSetup(){
        this.ticketRepository = new TicketRepositoryImpl(this.mockTicketCrudRepository);

        this.ticketEntity = new TicketEntity();
        this.ticketEntity.setId(1L);
        this.ticketEntity.setTicket("\"{\\\"b\\\":[1,5,8,10,11],\\\"g\\\":[49,52,55,57,58],\\\"i\\\":[17,21,24,25,29],\\\"n\\\":[35,41,42,44],\\\"o\\\":[65,67,72,74,62]}\"");
        this.ticketEntity.setContestNumberId(1001L);

        this.contestEntity = new ContestEntity();
        this.contestEntity.setId(1L);
        this.contestEntity.setContestNumber(1001);
        this.contestEntity.setRaffleNumbers("1,2,3,4,5");
        this.contestEntity.setNumber(1001);
    }

    @Test
    @DisplayName("Deve salvar um ticket e um contest")
    public void testSaveTicket(){
        TicketEntity savedTicket = this.ticketRepository.saveTicket(this.ticketEntity, this.contestEntity);

        Long id = savedTicket.getId();
        String ticket = savedTicket.getTicket();
        Long contestNumberId = savedTicket.getContestNumberId();
        boolean isGreaterThanZero = savedTicket.getId() > 0;

        assertNotNull(savedTicket);

        assertInstanceOf(TicketEntity.class, savedTicket);
        assertTrue(isGreaterThanZero);

        assertEquals(this.ticketEntity.getId(), id);
        assertEquals(this.ticketEntity.getTicket(), ticket);
        assertEquals(this.ticketEntity.getContestNumberId(), contestNumberId);
    }

    @Test
    @DisplayName("Deve retornar todos os tickets do contest")
    public void testGetAllTicketsByContest(){
        List<ContestEntity> emptyList = new ArrayList<>();
        List<TicketEntity> allTickets = this.ticketRepository.getAllTicketsByContest(this.contestEntity.getContestNumber());

        assertEquals(emptyList, allTickets);


        TicketEntity savedTicket;

        this.ticketEntity.setId(2L);
        savedTicket = this.ticketRepository.saveTicket(this.ticketEntity, this.contestEntity);

        this.ticketEntity.setId(3L);
        savedTicket = this.ticketRepository.saveTicket(this.ticketEntity, this.contestEntity);

        this.ticketEntity.setId(4L);
        savedTicket = this.ticketRepository.saveTicket(this.ticketEntity, this.contestEntity);

        this.ticketEntity.setId(5L);
        savedTicket = this.ticketRepository.saveTicket(this.ticketEntity, this.contestEntity);

        allTickets = this.ticketRepository.getAllTicketsByContest(this.contestEntity.getContestNumber());

        Long id = savedTicket.getId();
        String ticket = savedTicket.getTicket();
        Long contestNumberId = savedTicket.getContestNumberId();
        int listSize = allTickets.size();

        assertNotNull(allTickets);
        assertEquals(this.ticketEntity.getId(), id);
        assertEquals(this.ticketEntity.getTicket(), ticket);
        assertEquals(this.ticketEntity.getContestNumberId(), contestNumberId);
        assertEquals(4, listSize);
    }

}