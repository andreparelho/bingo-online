package com.app.bingoonline.service;

import com.app.bingoonline.entity.ContestEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ContestService {
    int generateContestNumber();
    ContestEntity createContest(int contestNumber);
    Map<String, Set<Integer>> createContest();
    int findContestById(int contestNumber);
    ContestEntity getAllRaffleNumbers(int contestNumber);
    ContestEntity saveRaffleNumber(int contestNumber, String raffleNumber);
    List<Integer> getAllContests();
}
