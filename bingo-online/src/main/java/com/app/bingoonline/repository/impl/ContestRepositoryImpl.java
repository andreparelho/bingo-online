package com.app.bingoonline.repository.impl;

import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.crud.ContestCrudRepository;
import com.app.entity.ContestEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContestRepositoryImpl implements ContestRepository {
    private final ContestCrudRepository contestCrudRepository;

    public ContestRepositoryImpl(ContestCrudRepository contestCrudRepository) {
        this.contestCrudRepository = contestCrudRepository;
    }

    @Override
    public ContestEntity saveContest(ContestEntity contest) {
        return this.contestCrudRepository.save(contest);
    }

    @Override
    public List<ContestEntity> getAllContests() {
        return null;
    }

    @Override
    public boolean findContestNumber(int contestNumber) {
        Optional<ContestEntity> contest = this.contestCrudRepository.findById((long) contestNumber);
        return contest.isPresent();
    }
}
