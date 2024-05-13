package com.app.bingoonline.repository.crud;

import com.app.bingoonline.entity.RaffleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RaffleCrudRepository extends CrudRepository<RaffleEntity, Long> {
    @Query("SELECT r FROM Raffle r WHERE r.contestId = ?1")
    RaffleEntity findContestId(long contestId);
}
