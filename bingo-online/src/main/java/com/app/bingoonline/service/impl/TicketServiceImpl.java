package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.exception.contest.ContestNotFoundException;
import com.app.bingoonline.model.*;
import com.app.bingoonline.controller.response.TicketListResponse;
import com.app.bingoonline.controller.response.TicketResponse;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.RaffleService;
import com.app.bingoonline.service.TicketService;
import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.mapper.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    private final B b;
    private final I i;
    private final N n;
    private final G g;
    private final O o;
    private final RaffleRepository raffleRepository;
    private final ContestService contestService;
    private final TicketRepository ticketRepository;
    private final Mapper mapper;

    public TicketServiceImpl(B b, I i, N n, G g, O o, RaffleRepository raffleRepository, ContestService contestService, TicketRepository ticketRepository, Mapper mapper) {
        this.b = b;
        this.i = i;
        this.n = n;
        this.g = g;
        this.o = o;
        this.raffleRepository = raffleRepository;
        this.contestService = contestService;
        this.ticketRepository = ticketRepository;
        this.mapper = mapper;
    }


    @Override
    public Map<String, Set<Integer>> generateTicket() throws JsonProcessingException {
        Map<String, Set<Integer>> ticket = generateCardTicket();

        int contestNumber = this.contestService.generateContestNumber();
        ContestEntity contest = this.contestService.createContest(contestNumber);

        TicketEntity ticketEntity = TicketEntity.builder()
                .ticket(this.mapper.mapToJson(ticket))
                .build();

        this.ticketRepository.saveTicket(ticketEntity, contest);
        ticket.put("Contest", Collections.singleton(contest.getContestNumber()));

        return ticket;
    }

    @Override
    public TicketResponse generateTicketByContestId(int contestNumber) throws Exception {
        int hasContestNumber = this.contestService.findContestById(contestNumber);

        if (hasContestNumber != contestNumber) {
            throw new ContestNotFoundException("contest not found");
        }

        Map<String, Set<Integer>> ticket = generateCardTicket();

        TicketEntity ticketEntity = TicketEntity.builder()
                .ticket(this.mapper.mapToJson(ticket))
                .build();

        ContestEntity contestEntity = ContestEntity.builder()
                .number(contestNumber)
                .contestNumber(contestNumber)
                .build();

        this.ticketRepository.saveTicket(ticketEntity, contestEntity);

        ticket.put("Contest", Collections.singleton(contestEntity.getContestNumber()));

        return new TicketResponse(ticket);
    }

    private void checkTicketsWinner(List<TicketEntity> tickets) {
        for (TicketEntity ticket : tickets){
            String ticketString = ticket.getTicket();
            var mapTicket = this.mapper.convertStringToMap(ticketString);
        }
    }

    @Override
    public TicketListResponse getAllTicketsByContest(int contestNumber) {
        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contestNumber);
        Map<Integer, List<TicketEntity>> allTickets = new HashMap<>();
        allTickets.put(contestNumber, tickets);
        return new TicketListResponse(allTickets);
    }

    private Map<String, Set<Integer>> generateCardTicket(){
        Map<String, Set<Integer>> ticket = new HashMap<>();
        ticket.put("b", this.b.generateTicketNumbers());
        ticket.put("i", this.i.generateTicketNumbers());
        ticket.put("n", this.n.generateTicketNumbers());
        ticket.put("g", this.g.generateTicketNumbers());
        ticket.put("o", this.o.generateTicketNumbers());
        return  ticket;
    }
}
