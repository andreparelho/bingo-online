package com.app.bingoonline.service;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.model.response.ContestResponseList;
import com.app.bingoonline.model.response.CreateContestResponse;

public interface ContestService {
    int generateContestNumber();
    ContestEntity createContest(int contestNumber);
    CreateContestResponse createContest();
    int findContestById(int contestNumber);
    ContestEntity getAllRaffleNumbers(int contestNumber);
    ContestEntity saveRaffleNumber(int contestNumber, String raffleNumber);
    ContestResponseList getAllContests();
}
