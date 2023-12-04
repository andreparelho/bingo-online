package com.app.bingoonline.repository;

import com.app.bingoonline.entity.ContestEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository {
    ContestEntity saveContest(ContestEntity contest);
    List<ContestEntity> getAllContests();
    ContestEntity findContestNumber(int contestNumber);
}
