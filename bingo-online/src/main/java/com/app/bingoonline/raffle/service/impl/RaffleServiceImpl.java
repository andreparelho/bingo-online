package com.app.bingoonline.raffle.service.impl;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.raffle.entity.RaffleEntity;
import com.app.bingoonline.ticket.entity.TicketEntity;
import com.app.bingoonline.infrastructure.util.mapper.Mapper;
import com.app.bingoonline.raffle.dto.response.RaffleResponse;
import com.app.bingoonline.contest.repository.ContestRepository;
import com.app.bingoonline.raffle.repository.RaffleRepository;
import com.app.bingoonline.ticket.repository.TicketRepository;
import com.app.bingoonline.game.service.GameService;
import com.app.bingoonline.raffle.service.RaffleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.app.bingoonline.raffle.constant.RaffleConstant.*;

@Service
public class RaffleServiceImpl implements RaffleService {
    private final RaffleRepository raffleRepository;
    private final Mapper mapper;
    private final Random random;
    private final TicketRepository ticketRepository;
    private final ContestRepository contestRepository;
    private final GameService gameService;

    public RaffleServiceImpl(RaffleRepository raffleRepository, Mapper mapper, Random random, TicketRepository ticketRepository, ContestRepository contestRepository, GameService gameService) {
        this.raffleRepository = raffleRepository;
        this.mapper = mapper;
        this.random = random;
        this.ticketRepository = ticketRepository;
        this.contestRepository = contestRepository;
        this.gameService = gameService;
    }

    @Override
    public RaffleResponse getRaffleNumber(int contestNumber) throws JsonProcessingException {
        ContestEntity contest = this.contestRepository.findContestNumber(contestNumber);

        RaffleEntity raffle = this.raffleRepository.getRaffle(contestNumber);

        List<Integer> listNumbers = this.mapper.convertStringToList(raffle.getRaffleNumbers());
        int numSortedIndex = random.nextInt(listNumbers.size());
        int numSorted = listNumbers.get(numSortedIndex);

        List<TicketEntity> tickets = this.ticketRepository.getAllTicketsByContest(contest.getContestNumber());
        this.gameService.removeSortedNumberFromTickets(numSorted, tickets, contest);

        String raffleSortedNumbers = raffle.getRaffleSortedNumbers();
        boolean checkWinners = this.checkSortedNumbers(raffleSortedNumbers);
        if (checkWinners){
            if (!contest.isGameOneWinner()) {
                Optional<UUID> winnerGameOne = this.gameService.checkWinnerGameOne(contest);
                boolean existWinner = winnerGameOne.isPresent();
                if (existWinner) {
                    return new RaffleResponse(WINNER_MESSAGE + winnerGameOne);
                }
            } else {
                ContestEntity winnerGame = this.gameService.checkWinnerGame(contestNumber);
                boolean existWinner = winnerGame.getTickerWinnerGame() != null;
                if (existWinner) {
                    return new RaffleResponse(WINNER_GAME_MESSAGE + winnerGame.getTicketWinnerGameOne());
                }
            }
        }

        this.removeNumberFromList(numSorted, numSortedIndex, listNumbers, raffle);

        return new RaffleResponse(String.valueOf(numSorted));
    }

    @Override
    public RaffleEntity getRaffle(int contestNumber) {
        return this.raffleRepository.getRaffle(contestNumber);
    }

    @Override
    public void removeNumberFromList(int numSorted, int numSortedIndex, List<Integer> listNumbers, RaffleEntity raffle) {
        String stringNumSorted = "" + numSorted;
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
    }

    private boolean checkSortedNumbers(String raffleSortedNumbers) {
        if (raffleSortedNumbers == null){
            return false;
        }

        List<Integer> raffleList = this.mapper.convertStringToList(raffleSortedNumbers);
        return raffleList.size() > 4;
    }

    private boolean verifySortedNumbers(String raffleSortedNumbers) {
        return raffleSortedNumbers == null;
    }
}
