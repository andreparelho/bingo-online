package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.mapper.BingoCardMapper;
import com.app.bingoonline.mapper.Mapper;
import com.app.bingoonline.model.ticket.TicketModel;
import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameServiceImpl implements GameService {
    private final Mapper mapper;
    private final ContestRepository contestRepository;
    private final TicketRepository ticketRepository;
    private final RaffleRepository raffleRepository;
    private final BingoCardMapper bingoCardMapper;

    public GameServiceImpl(Mapper mapper, ContestRepository contestRepository, TicketRepository ticketRepository, RaffleRepository raffleRepository, BingoCardMapper bingoCardMapper) {
        this.mapper = mapper;
        this.contestRepository = contestRepository;
        this.ticketRepository = ticketRepository;
        this.raffleRepository = raffleRepository;
        this.bingoCardMapper = bingoCardMapper;
    }

    @Override
    public Optional<UUID> checkWinnerGameOne(ContestEntity contest) throws JsonProcessingException {
        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contest.getContestNumber());

        Optional<TicketEntity> vertical = this.checkVertical(tickets);
        if (vertical.isEmpty()) {
            return Optional.empty();
        }

        contest.setGameOneWinner(true);
        contest.setTicketWinnerGameOne(vertical.get().getId());
        this.contestRepository.saveContest(contest);

        return Optional.ofNullable(vertical.get().getId());
    }

    @Override
    public ContestEntity checkWinnerGame(int contestNumber) {
        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contestNumber);
        RaffleEntity raffle = this.raffleRepository.getRaffle(contestNumber);
//        TODO -> Criar validacao para cartela cheia, e salvar o campo gameOneWinner na entidade Contest.
        //        TODO -> Criar validacao para tinquina, e salvar o campo TicketWinner com ID do ticket na entidade Contest

        return null;
    }

    @Override
    public void removeSortedNumberFromTickets(int sortedNumber, List<TicketEntity> ticketList, ContestEntity contest) throws JsonProcessingException {
        for (TicketEntity ticket : ticketList){
            Map<String, Set<Integer>> ticketMap = this.mapper.jsonToMap(ticket.getTicket());
            Map<String, Set<Integer>> updatedTicket = removeNumberFromMap(ticketMap, sortedNumber);

            String updateMapString = this.mapper.mapToJson(updatedTicket);

            boolean isMapEquals = ticket.getTicket().equals(updateMapString);
            if (!isMapEquals){
                ticket.setTicket(updateMapString);
                this.ticketRepository.saveTicket(ticket, contest);
            }
        }
    }

    public Map<String, Set<Integer>> removeNumberFromMap(Map<String, Set<Integer>> numberMap, int sortedNumber) {
        for (Map.Entry<String, Set<Integer>> entry : numberMap.entrySet()) {
            Set<Integer> entryValue = entry.getValue();
            if (entryValue.contains(sortedNumber)) {
                List<Integer> tempList = new ArrayList<>(entryValue);

                for (int i = 0; i < tempList.size(); i++) {
                    if (tempList.get(i).equals(sortedNumber)) {
                        tempList.set(i, 0);
                        break;
                    }
                }

                entry.setValue(new LinkedHashSet<>(tempList));
                break;
            }
        }
        return numberMap;
    }

    public Optional<TicketEntity> checkVertical(List<TicketEntity> tickets) throws JsonProcessingException {
        TicketEntity ticketWinner = new TicketEntity();
        for (TicketEntity ticket : tickets) {
            TicketModel ticketMap = this.bingoCardMapper.convertStringToBingoCard(ticket.getTicket());

            if (!checkColumn(ticketMap.getB()) ||
                    !checkColumn(ticketMap.getI()) ||
                    !checkColumn(ticketMap.getN()) ||
                    !checkColumn(ticketMap.getG()) ||
                    !checkColumn(ticketMap.getO())) {
                return Optional.empty();
            }
            ticketWinner = ticket;
        }
        return Optional.of(ticketWinner);
    }

    private boolean checkColumn(Map<String, List<Integer>> column) {
        for (List<Integer> values : column.values()) {
            for (Integer value : values) {
                if (value != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkHorizontal(Map<String, List<Integer>> card) {
        return false;
    }

    public boolean checkDiagonal(Map<String, List<Integer>> card) {
        return false;
    }

    public boolean checkTicket(Map<String, List<Integer>> card) {
        return false;
    }
}
