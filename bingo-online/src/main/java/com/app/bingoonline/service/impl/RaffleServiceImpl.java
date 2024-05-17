package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.mapper.Mapper;
import com.app.bingoonline.controller.response.RaffleResponse;
import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.service.RaffleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class RaffleServiceImpl implements RaffleService {
    private final RaffleRepository raffleRepository;
    private final Mapper mapper;
    private final Random random;
    private final TicketRepository ticketRepository;
    private final ContestRepository contestRepository;

    public RaffleServiceImpl(RaffleRepository raffleRepository, Mapper mapper, Random random, TicketRepository ticketRepository, ContestRepository contestRepository) {
        this.raffleRepository = raffleRepository;
        this.mapper = mapper;
        this.random = random;
        this.ticketRepository = ticketRepository;
        this.contestRepository = contestRepository;
    }


    @Override
    public RaffleResponse getRaffleNumber(int contestNumber) {
        ContestEntity contest = this.contestRepository.findContestNumber(contestNumber);
        RaffleEntity raffle = this.raffleRepository.getRaffle(contestNumber);
        String raffleSortedNumbers = raffle.getRaffleSortedNumbers();

        boolean checkWinners = this.checkSortedNumbers(raffleSortedNumbers);
        if (checkWinners){
            if (!contest.isGameOneWinner()) {
                boolean winnerGameOne =  this.checkWinnerGame(contestNumber);
            }
        }

        boolean checkRaffle = this.isFinalizedRaffle(raffleSortedNumbers);
        if (checkRaffle){
            return new RaffleResponse("Raffle is ended");
        }

        List<Integer> listNumbers = this.mapper.convertStringToList(raffle.getRaffleNumbers());

        int numSortedIndex = random.nextInt(listNumbers.size());
        int numSorted = listNumbers.get(numSortedIndex);
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
        return true;
    }

    public boolean checkWinnerGameOne(int contestNumber){
        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contestNumber);
        RaffleEntity raffle = this.raffleRepository.getRaffle(contestNumber);
//        TODO -> Criar validacao para tinquina, e salvar o campo gameOneWinner na entidade Contest.

        for (TicketEntity ticket : tickets){
            String ticketString = ticket.getTicket();
            Map<String, Integer> mapTicket = this.mapper.convertStringToMap(ticketString);


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
}
