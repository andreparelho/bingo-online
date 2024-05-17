package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.mapper.ConverterMapper;
import com.app.bingoonline.model.response.RaffleResponse;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.RaffleService;
import com.app.bingoonline.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class RaffleServiceImpl implements RaffleService {
    private final RaffleRepository repository;
    private final ConverterMapper mapper;
    private final Random random;
    private final TicketService ticketService;
    private final ContestService contestService;

    public RaffleServiceImpl(RaffleRepository repository, ConverterMapper mapper, Random random, TicketService ticketService, ContestService contestService) {
        this.repository = repository;
        this.mapper = mapper;
        this.random = random;
        this.ticketService = ticketService;
        this.contestService = contestService;
    }


    @Override
    public RaffleResponse getRaffleNumber(int contestNumber) {
        ContestEntity contest = this.contestService.findContest(contestNumber);
        RaffleEntity raffle = this.repository.getRaffle(contestNumber);
        String raffleSortedNumbers = raffle.getRaffleSortedNumbers();

        boolean checkWinners = this.checkSortedNumbers(raffleSortedNumbers);
        if (checkWinners){
            if (!contest.isGameOneWinner()) {
                boolean winnerGameOne =  this.ticketService.checkWinnerGame(contestNumber);
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
        this.repository.updateRaffle(raffle);

        return new RaffleResponse(String.valueOf(numSorted));
    }

    @Override
    public RaffleEntity getRaffle(int contestNumber) {
        return this.repository.getRaffle(contestNumber);
    }

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
