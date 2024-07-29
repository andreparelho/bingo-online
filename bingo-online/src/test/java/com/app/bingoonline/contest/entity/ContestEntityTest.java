package com.app.bingoonline.contest.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ContestEntityTest {

    @Test
    @DisplayName("Deve criar com sucesso um contest")
    public void testContestEntityShouldReturnContestCreatedWithSuccess(){
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(UUID.randomUUID());
        contestEntity.setNumber(new Random().nextInt(1000, 9999));
        contestEntity.setContestNumber(new Random().nextInt(1000, 9999));
        contestEntity.setInitiated(true);
        contestEntity.setCreatedAt(Instant.now());
        contestEntity.setUpdatedAt(Instant.now());
        contestEntity.setFinishedAt(Instant.now());
        contestEntity.setGameOneWinner(false);
        contestEntity.setGameWinner(true);
        contestEntity.setTicketWinnerGameOne(UUID.randomUUID());
        contestEntity.setTickerWinnerGame(UUID.randomUUID());

        assertNotNull(contestEntity);
        assertInstanceOf(UUID.class, contestEntity.getId());
        assertInstanceOf(Integer.class, contestEntity.getNumber());
        assertInstanceOf(Integer.class, contestEntity.getContestNumber());
        assertInstanceOf(Boolean.class, contestEntity.isInitiated());
        assertInstanceOf(Instant.class, contestEntity.getCreatedAt());
        assertInstanceOf(Instant.class, contestEntity.getUpdatedAt());
        assertInstanceOf(Instant.class, contestEntity.getFinishedAt());
        assertInstanceOf(Boolean.class, contestEntity.isGameOneWinner());
        assertInstanceOf(Boolean.class, contestEntity.isGameWinner());
        assertInstanceOf(UUID.class, contestEntity.getTickerWinnerGame());
        assertInstanceOf(UUID.class, contestEntity.getTicketWinnerGameOne());
        assertTrue(contestEntity.isInitiated());
        assertFalse(contestEntity.isGameOneWinner());
        assertTrue(contestEntity.isGameWinner());
    }

}