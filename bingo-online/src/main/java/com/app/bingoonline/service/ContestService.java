package com.app.bingoonline.service;

import com.app.entity.ContestEntity;

public interface ContestService {
    int generateContestNumber();
    ContestEntity createContest(int contestNumber);
}
