package com.app.bingoonline.repository.impl;

import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.crud.ContestCrudRepository;
import com.app.bingoonline.entity.ContestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContestRepositoryImpl implements ContestRepository {
    private final ContestCrudRepository contestCrudRepository;

    @Autowired
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
    public ContestEntity findContestNumber(int contestNumber) {
        return this.contestCrudRepository.findByNumber(contestNumber);
    }

}
