package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.mapper.BingoCardMapper;
import com.app.bingoonline.mapper.Mapper;
import com.app.bingoonline.controller.response.RaffleResponse;
import com.app.bingoonline.model.BingoCard;
import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.service.RaffleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RaffleServiceImpl implements RaffleService {
    private final RaffleRepository raffleRepository;
    private final Mapper mapper;
    private final Random random;
    private final TicketRepository ticketRepository;
    private final ContestRepository contestRepository;
    private final BingoCardMapper bingoCardMapper;

    public RaffleServiceImpl(RaffleRepository raffleRepository, Mapper mapper, Random random, TicketRepository ticketRepository, ContestRepository contestRepository, BingoCardMapper bingoCardMapper) {
        this.raffleRepository = raffleRepository;
        this.mapper = mapper;
        this.random = random;
        this.ticketRepository = ticketRepository;
        this.contestRepository = contestRepository;
        this.bingoCardMapper = bingoCardMapper;
    }


    @Override
    public RaffleResponse getRaffleNumber(int contestNumber) throws JsonProcessingException {
        ContestEntity contest = this.contestRepository.findContestNumber(contestNumber);
        RaffleEntity raffle = this.raffleRepository.getRaffle(contestNumber);
        String raffleSortedNumbers = raffle.getRaffleSortedNumbers();

        List<Integer> listNumbers = this.mapper.convertStringToList(raffle.getRaffleNumbers());
        int numSortedIndex = random.nextInt(listNumbers.size());
        int numSorted = listNumbers.get(numSortedIndex);

        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contestNumber);
        this.removeSortedNumberFromTickets(numSorted, tickets, contestNumber);

        //boolean checkWinners = this.checkSortedNumbers(raffleSortedNumbers);
        boolean winnerGameOne =  this.checkWinnerGameOne(contestNumber);

//        if (checkWinners){
//            if (!contest.isGameOneWinner()) {
//                boolean winnerGameOne =  this.checkWinnerGameOne(contestNumber);
//            }
//        }

        boolean checkRaffle = this.isFinalizedRaffle(raffleSortedNumbers);
        if (checkRaffle){
            return new RaffleResponse("Raffle is ended");
        }


        String stringNumSorted = String.valueOf(numSorted);
        listNumbers.remove(numSortedIndex);

        String list = this.mapper.convertListToString(Collections.singletonList(String.valueOf(listNumbers)));
        String stringList = list.replace("[", "").replace("]", "");
        raffle.setRaffleNumbers(stringList);

        boolean sortedNumbers = this.verifySortedNumbers(raffle.getRaffleSortedNumbers());
        if (!sortedNumbers){
            stringNumSorted = raffle.getRaffleSortedNumbers() + ", " + numSorted;
        }

        raffle.setRaffleSortedNumbers(stringNumSorted);
        this.raffleRepository.updateRaffle(raffle);

        return new RaffleResponse(String.valueOf(numSorted));
    }

    @Override
    public RaffleEntity getRaffle(int contestNumber) {
        return this.raffleRepository.getRaffle(contestNumber);
    }

    public boolean checkWinnerGame(int contestNumber){
        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contestNumber);
        RaffleEntity raffle = this.raffleRepository.getRaffle(contestNumber);
//        TODO -> Criar validacao para cartela cheia, e salvar o campo gameOneWinner na entidade Contest.
    //        TODO -> Criar validacao para tinquina, e salvar o campo TicketWinner com ID do ticket na entidade Contest

        return true;
    }

    public boolean checkWinnerGameOne(int contestNumber) throws JsonProcessingException {
        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contestNumber);
        RaffleEntity raffle = this.raffleRepository.getRaffle(contestNumber);
//        TODO -> Criar validacao para tinquina, e salvar o campo gameOneWinner na entidade Contest.
//        TODO -> Criar validacao para tinquina, e salvar o campo ticketGameOneWinner com ID do ticket na entidade Contest

        for (TicketEntity ticket : tickets){
            String ticketString = ticket.getTicket();

            BingoCard card = this.bingoCardMapper.convertStringToBingoCard(ticketString);



        }

        return true;
    }

    //        TODO -> Criar metodo para tirar o numero sorteado da cartela com remove e salvando (facilitar as validacoes de ganhadores)

    private boolean checkSortedNumbers(String raffleSortedNumbers) {
        List<Integer> raffleList = this.mapper.convertStringToList(raffleSortedNumbers);

        return raffleList.size() > 4;
    }

    private boolean verifySortedNumbers(String raffleSortedNumbers) {
        return raffleSortedNumbers.isEmpty() || raffleSortedNumbers.isBlank();
    }

    private boolean isFinalizedRaffle(String raffleNumbers){
        List<Integer> raffleList = this.mapper.convertStringToList(raffleNumbers);

        return raffleList.size() >= 24;
    }

    public void removeSortedNumberFromTickets(int sortedNumber, List<TicketEntity> ticketList, int contestNumber) throws JsonProcessingException {
        ContestEntity contest = this.contestRepository.findContestNumber(contestNumber);
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
                entryValue.add(0); //TODO: MUDAR PARA ZERO
                break;
            }
        }
        return numberMap;
    }

}
