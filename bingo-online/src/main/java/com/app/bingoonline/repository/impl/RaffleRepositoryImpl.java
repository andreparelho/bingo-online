package com.app.bingoonline.repository.impl;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.repository.crud.RaffleCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RaffleRepositoryImpl implements RaffleRepository {
    private final RaffleCrudRepository crudRepository;

    public RaffleRepositoryImpl(RaffleCrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public RaffleEntity getRaffleNumbersByContestId(long contest) {
        RaffleEntity raffle = this.crudRepository.findContestId(contest);
        return raffle;
    }

    @Override
    public void saveRaffle(RaffleEntity raffle) {
        this.crudRepository.save(raffle);
    }

    @Override
    public void updateRaffle(RaffleEntity raffle) {
        this.crudRepository.save(raffle);
    }
}
