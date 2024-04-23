package com.app.bingoonline.model;

import lombok.Builder;

@Builder
public class ContestModel {
    private int number;
    private String raffleNumbers;
    private int contestNumber;

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
