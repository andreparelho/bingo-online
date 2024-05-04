package com.app.bingoonline.model.dto;


import lombok.Builder;

import java.util.UUID;

@Builder
public class ContestDTO {
    private UUID id;
    private int number;
    private String raffleNumbers;
    private int contestNumber;

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

    public String getRaffleNumbers() {
        return raffleNumbers;
    }

    public void setRaffleNumbers(String raffleNumbers) {
        this.raffleNumbers = raffleNumbers;
    }

    public int getContestNumber() {
        return contestNumber;
    }

    public void setContestNumber(int contestNumber) {
        this.contestNumber = contestNumber;
    }
}