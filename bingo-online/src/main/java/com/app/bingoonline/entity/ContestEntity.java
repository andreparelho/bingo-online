package com.app.bingoonline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Table(name = "contests")
@Entity(name = "Contest")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "contest_id")
    private UUID id;
    private int number;
    private int contestNumber;
    private boolean initiated;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant finishedAt;
    private boolean gameOneWinner;
    private boolean gameWinner;
    private UUID ticketWinnerGameOne;
    private UUID tickerWinnerGame;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getContestNumber() {
        return contestNumber;
    }

    public void setContestNumber(int contestNumber) {
        this.contestNumber = contestNumber;
    }

    public boolean isInitiated() {
        return initiated;
    }

    public void setInitiated(boolean initiated) {
        this.initiated = initiated;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public boolean isGameOneWinner() {
        return gameOneWinner;
    }

    public void setGameOneWinner(boolean gameOneWinner) {
        this.gameOneWinner = gameOneWinner;
    }

    public boolean isGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(boolean gameWinner) {
        this.gameWinner = gameWinner;
    }

    public UUID getTicketWinnerGameOne() {
        return ticketWinnerGameOne;
    }

    public void setTicketWinnerGameOne(UUID ticketWinnerGameOne) {
        this.ticketWinnerGameOne = ticketWinnerGameOne;
    }

    public UUID getTickerWinnerGame() {
        return tickerWinnerGame;
    }

    public void setTickerWinnerGame(UUID tickerWinnerGame) {
        this.tickerWinnerGame = tickerWinnerGame;
    }
}
