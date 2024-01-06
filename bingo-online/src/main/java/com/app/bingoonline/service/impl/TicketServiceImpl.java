package com.app.bingoonline.service.impl;

import com.app.bingoonline.model.extds.*;
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
        Map<String, Set<Integer>> ticket = generateCardTicket();

        int contestNumber = this.contestService.generateContestNumber();
        ContestEntity contest = this.contestService.createContest(contestNumber);

        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTicket(this.converter.mapToJson(ticket));

        this.ticketRepository.saveTicket(ticketEntity, contest);
        ticket.put("Contest", Collections.singleton(contest.getContestNumber()));

        return ticket;
    }

    @Override
    public Map<String, Set<Integer>> generateTicketByContestId(int contestNumber) throws Exception {
        int hasContestNumber = this.contestService.findContestById(contestNumber);

        if (hasContestNumber == contestNumber) {
            ContestEntity contestEntity = new ContestEntity();
            Map<String, Set<Integer>> ticket = generateCardTicket();
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setTicket(this.converter.mapToJson(ticket));

            contestEntity.setContestNumber(contestNumber);
            this.ticketRepository.saveTicket(ticketEntity, contestEntity);

            ticket.put("Contest", Collections.singleton(contestEntity.getContestNumber()));
            return ticket;
        }

        throw new Exception("Esse concurso não existe");
    }

    @Override
    public String getRaffleNumber(int contestNumber) {
        Random random = new Random();
        List<String> updateRaffle = new ArrayList<>();

        String rafNum = String.valueOf(random.nextInt(1, 75));

        ContestEntity actualContest = this.contestService.getAllRaffleNumbers(contestNumber);
        String raffleNumberString = actualContest.getRaffleNumbers() != null ? actualContest.getRaffleNumbers() : null;

        if (actualContest.getRaffleNumbers() != null){
            List<Integer> numbers = this.converter.convertStringToList(actualContest.getRaffleNumbers());
            for (Integer number : numbers) {
                if (Objects.equals(number, Integer.valueOf(rafNum))) {
                    rafNum = String.valueOf(random.nextInt(1, 75));
                    assert raffleNumberString != null;

                    updateRaffle.add(raffleNumberString);
                    updateRaffle.add(rafNum);

                    this.contestService.saveRaffleNumber(contestNumber, rafNum);

                    return rafNum;
                }
            }

            updateRaffle.add(raffleNumberString);
            updateRaffle.add(rafNum);

            rafNum = this.converter.convertListToString(updateRaffle);
        }

        this.contestService.saveRaffleNumber(contestNumber, rafNum);

        return rafNum;
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
