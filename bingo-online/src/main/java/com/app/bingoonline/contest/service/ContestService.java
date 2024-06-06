package com.app.bingoonline.contest.service;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.contest.dto.response.ContestResponseList;
import com.app.bingoonline.contest.dto.response.CreateContestResponse;

public interface ContestService {
    int generateContestNumber();
    ContestEntity createContest(int contestNumber);
    CreateContestResponse createContest();
    int findContestById(int contestNumber);
    ContestEntity findContest(int contestNumber);
    ContestEntity getAllRaffleNumbers(int contestNumber);
    ContestEntity saveRaffleNumber(int contestNumber, String raffleNumber);
    ContestResponseList getAllContests();
}
