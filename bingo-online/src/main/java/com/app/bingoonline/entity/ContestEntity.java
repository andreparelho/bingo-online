package com.app.bingoonline.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contests")
public class ContestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int number;
    private String raffleNumbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getContestNumber() {
        return number;
    }

    public void setContestNumber(int number) {
        this.number = number;
    }

    public String getRaffleNumbers() {
        return raffleNumbers;
    }

    public void setRaffleNumbers(String raffleNumbers) {
        this.raffleNumbers = raffleNumbers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
