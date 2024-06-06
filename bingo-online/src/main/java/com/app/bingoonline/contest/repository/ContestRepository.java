package com.app.bingoonline.contest.repository;

import com.app.bingoonline.contest.entity.ContestEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository {
    ContestEntity saveContest(ContestEntity contest);
    List<ContestEntity> getAllContests();
    ContestEntity findContestNumber(int contestNumber);
    ContestEntity saveRaffleNumberOnContest(int contestNumber, String raffleNumber);
}
