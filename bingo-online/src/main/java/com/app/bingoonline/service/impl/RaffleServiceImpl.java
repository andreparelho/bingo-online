package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.mapper.ConverterMapper;
import com.app.bingoonline.model.response.RaffleResponse;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.RaffleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class RaffleServiceImpl implements RaffleService {
    private final ContestService contestService;
    private final RaffleRepository repository;
    private final ConverterMapper mapper;
    private final Random random;

    public RaffleServiceImpl(ContestService contestService, RaffleRepository repository, ConverterMapper mapper, Random random) {
        this.contestService = contestService;
        this.repository = repository;
        this.mapper = mapper;
        this.random = random;
    }


    @Override
    public RaffleResponse getRaffleNumber(int contestNumber) {
        RaffleEntity raffle = this.repository.getRaffleNumbersByContestId(contestNumber);

        boolean checkRaffle = this.isFinalizedRaffle(raffle.getRaffleNumbers());

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

        if (!Objects.equals(raffle.getRaffleSortedNumbers(), null)){
            stringNumSorted = raffle.getRaffleSortedNumbers() + ", " + numSorted;
        }

        raffle.setRaffleSortedNumbers(stringNumSorted);
        this.repository.updateRaffle(raffle);

        return new RaffleResponse(String.valueOf(numSorted));
    }

    private boolean isFinalizedRaffle(String raffleNumbers){
        return raffleNumbers.isBlank() || raffleNumbers.isEmpty();
    }
}
