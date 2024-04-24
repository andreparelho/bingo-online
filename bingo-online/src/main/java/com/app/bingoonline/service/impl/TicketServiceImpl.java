package com.app.bingoonline.service.impl;

import com.app.bingoonline.model.Raffle;
import com.app.bingoonline.model.ticketsLetters.*;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.TicketService;
import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.converter.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    public final B b;
    public final I i;
    public final N n;
    public final G g;
    public final O o;
    public final Raffle raffle;
    private final ContestService contestService;
    private final TicketRepository ticketRepository;
    private final Converter converter;

    @Autowired
    public TicketServiceImpl(B b, I i, N n, G g, O o, Raffle raffle, ContestService contestService, TicketRepository ticketRepository, Converter converter) {
        this.b = b;
        this.i = i;
        this.n = n;
        this.g = g;
        this.o = o;
        this.raffle = raffle;
        this.contestService = contestService;
        this.ticketRepository = ticketRepository;
        this.converter = converter;
    }

    @Override
    public Map<String, Set<Integer>> generateTicket() throws JsonProcessingException {
        Map<String, Set<Integer>> ticket = generateCardTicket();

        int contestNumber = this.contestService.generateContestNumber();
        ContestEntity contest = this.contestService.createContest(contestNumber);

        TicketEntity ticketEntity = TicketEntity.builder()
                .ticket(this.converter.mapToJson(ticket))
                .build();

        this.ticketRepository.saveTicket(ticketEntity, contest);
        ticket.put("Contest", Collections.singleton(contest.getContestNumber()));

        return ticket;
    }

    @Override
    public Map<String, Set<Integer>> generateTicketByContestId(int contestNumber) throws Exception {
        int hasContestNumber = this.contestService.findContestById(contestNumber);

        if (hasContestNumber == contestNumber) {
            Map<String, Set<Integer>> ticket = generateCardTicket();

            TicketEntity ticketEntity = TicketEntity.builder()
                    .ticket(this.converter.mapToJson(ticket))
                    .build();

            ContestEntity contestEntity = ContestEntity.builder()
                    .number(contestNumber)
                    .contestNumber(contestNumber)
                    .build();

            this.ticketRepository.saveTicket(ticketEntity, contestEntity);

            ticket.put("Contest", Collections.singleton(contestEntity.getContestNumber()));
            return ticket;
        }

        throw new Exception("Esse concurso não existe");
    }

    @Override
    public String getRaffleNumber(int contestNumber) {
        String randomRaffleNumber = this.raffle.createRandomRaffleNumber();

        ContestEntity actualContest = this.raffle.getAllRaffleNumbers(contestNumber);
        boolean hasRaffle = this.raffle.verifyContest(actualContest.getRaffleNumbers());

        if (hasRaffle){
            String raffleNumbersDatabase = actualContest.getRaffleNumbers();
            List<Integer> numbers = this.converter.convertStringToList(raffleNumbersDatabase);

            String raffle = this.raffle.verifyNumbers(numbers, randomRaffleNumber);
            List<String> updateRaffle = this.raffle.updateRaffleList(raffleNumbersDatabase, raffle);

            this.contestService.saveRaffleNumber(contestNumber, randomRaffleNumber);

            randomRaffleNumber = this.converter.convertListToString(updateRaffle);
        }
        this.contestService.saveRaffleNumber(contestNumber, randomRaffleNumber);

        return randomRaffleNumber;
    }

    @Override
    public Map<Integer, List<TicketEntity>> getAllTicketsByContest(int contestNumber) {
        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contestNumber);
        Map<Integer, List<TicketEntity>> allTickets = new HashMap<>();
        allTickets.put(contestNumber, tickets);
        return allTickets;
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
