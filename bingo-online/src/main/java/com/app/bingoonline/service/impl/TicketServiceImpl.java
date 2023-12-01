package com.app.bingoonline.service.impl;

import com.app.bingoonline.model.extds.*;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.TicketService;
import com.app.entity.ContestEntity;
import com.app.entity.TicketEntity;
import com.app.entity.converter.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    private final B b;
    public final I i;
    public final N n;
    public final G g;
    public final O o;
    private final ContestService contestService;
    private final TicketRepository ticketRepository;
    private final Converter converter;

    @Autowired
    public TicketServiceImpl(B b, I i, N n, G g, O o, ContestService contestService, TicketRepository ticketRepository, Converter converter) {
        this.b = b;
        this.i = i;
        this.n = n;
        this.g = g;
        this.o = o;
        this.contestService = contestService;
        this.ticketRepository = ticketRepository;
        this.converter = converter;
    }

    @Override
    public Map<String, Set<Integer>> generateTicket() throws JsonProcessingException {
        Map<String, Set<Integer>> ticket = new HashMap<>();
        ticket.put("b", this.b.generateTicketNumbers());
        ticket.put("i", this.i.generateTicketNumbers());
        ticket.put("n", this.n.generateTicketNumbers());
        ticket.put("g", this.g.generateTicketNumbers());
        ticket.put("o", this.o.generateTicketNumbers());

        int contestNumber = this.contestService.generateContestNumber();
        ContestEntity contest = this.contestService.createContest(contestNumber);

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicket(this.converter.mapToJson(ticket));

        this.ticketRepository.saveTicket(ticketEntity, contest);

        return ticket;
    }
}
