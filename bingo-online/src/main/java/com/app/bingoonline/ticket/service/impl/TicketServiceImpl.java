package com.app.bingoonline.ticket.service.impl;

import com.app.bingoonline.contest.exception.ContestNotFoundException;
import com.app.bingoonline.ticket.dto.response.CreatedTicketResponse;
import com.app.bingoonline.ticket.dto.response.TicketListResponse;
import com.app.bingoonline.ticket.dto.response.TicketResponse;
import com.app.bingoonline.raffle.repository.RaffleRepository;
import com.app.bingoonline.ticket.factory.impl.*;
import com.app.bingoonline.ticket.repository.TicketRepository;
import com.app.bingoonline.contest.service.ContestService;
import com.app.bingoonline.ticket.service.TicketService;
import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.ticket.entity.TicketEntity;
import com.app.bingoonline.infrastructure.util.mapper.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    private final BFactoryImpl bFactoryImpl;
    private final IFactoryImpl iFactoryImpl;
    private final NFactoryImpl nFactoryImpl;
    private final GFactoryImpl gFactoryImpl;
    private final OFactoryImpl oFactoryImpl;
    private final RaffleRepository raffleRepository;
    private final ContestService contestService;
    private final TicketRepository ticketRepository;
    private final Mapper mapper;

    public TicketServiceImpl(BFactoryImpl bFactoryImpl, IFactoryImpl iFactoryImpl, NFactoryImpl nFactoryImpl,
                             GFactoryImpl gFactoryImpl, OFactoryImpl oFactoryImpl, RaffleRepository raffleRepository,
                             ContestService contestService, TicketRepository ticketRepository, Mapper mapper) {
        this.bFactoryImpl = bFactoryImpl;
        this.iFactoryImpl = iFactoryImpl;
        this.nFactoryImpl = nFactoryImpl;
        this.gFactoryImpl = gFactoryImpl;
        this.oFactoryImpl = oFactoryImpl;
        this.raffleRepository = raffleRepository;
        this.contestService = contestService;
        this.ticketRepository = ticketRepository;
        this.mapper = mapper;
    }


    @Override
    public CreatedTicketResponse generateTicket() throws JsonProcessingException {
        Map<String, List<Integer>> ticket = generateCardTicket();

        int contestNumber = this.contestService.generateContestNumber();
        ContestEntity contest = this.contestService.createContest(contestNumber);

        TicketEntity ticketEntity = TicketEntity.builder()
                .ticket(this.mapper.mapToJson(ticket))
                .build();

        this.ticketRepository.saveTicket(ticketEntity, contest);

        return new CreatedTicketResponse("Contest", ticket);
    }

    @Override
    public TicketResponse generateTicketByContestId(int contestNumber) throws Exception {
        int hasContestNumber = this.contestService.findContestById(contestNumber);

        if (hasContestNumber != contestNumber) {
            throw new ContestNotFoundException("contest not found");
        }

        Map<String, List<Integer>> ticket = generateCardTicket();

        TicketEntity ticketEntity = TicketEntity.builder()
                .ticket(this.mapper.mapToJson(ticket))
                .build();

        ContestEntity contestEntity = ContestEntity.builder()
                .number(contestNumber)
                .contestNumber(contestNumber)
                .build();

        this.ticketRepository.saveTicket(ticketEntity, contestEntity);

        return new TicketResponse("Contest", ticket);
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

    private Map<String, List<Integer>> generateCardTicket(){
        Map<String, List<Integer>> ticket = new HashMap<>();
        ticket.put("b", this.bFactoryImpl.generateTicketNumbers());
        ticket.put("i", this.iFactoryImpl.generateTicketNumbers());
        ticket.put("n", this.nFactoryImpl.generateTicketNumbers());
        ticket.put("g", this.gFactoryImpl.generateTicketNumbers());
        ticket.put("o", this.oFactoryImpl.generateTicketNumbers());
        return  ticket;
    }
}
