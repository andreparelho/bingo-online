package com.app.bingoonline.repository;

import com.app.bingoonline.entity.RaffleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RaffleRepository {
    RaffleEntity getRaffleNumbersByContestId(long contest);
    void saveRaffle(RaffleEntity raffle);
    void updateRaffle(RaffleEntity raffle);
}
