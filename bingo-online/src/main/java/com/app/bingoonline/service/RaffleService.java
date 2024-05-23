package com.app.bingoonline.service;

import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.controller.response.RaffleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface RaffleService {
    RaffleResponse getRaffleNumber(int contestNumber) throws JsonProcessingException;
    RaffleEntity getRaffle(int contestNumber);
    void removeNumberFromList(int numSorted, int numSortedIndex, List<Integer> listNumbers, RaffleEntity raffle);
}
