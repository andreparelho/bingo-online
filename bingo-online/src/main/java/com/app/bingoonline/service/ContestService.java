package com.app.bingoonline.service;

import com.app.bingoonline.entity.ContestEntity;

public interface ContestService {
    int generateContestNumber();
    ContestEntity createContest(int contestNumber);
    int findContestById(int contestNumber);
}
