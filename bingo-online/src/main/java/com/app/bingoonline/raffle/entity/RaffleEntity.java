package com.app.bingoonline.raffle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "raffle")
@Entity(name = "Raffle")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RaffleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raffle_id")
    private Long id;
    private Long contestId;
    @Column(name = "raffle_numbers", columnDefinition = "TEXT")
    private String raffleNumbers;
    @Column(name = "sorted_numbers", columnDefinition = "TEXT")
    private String raffleSortedNumbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getRaffleNumbers() {
        return raffleNumbers;
    }

    public void setRaffleNumbers(String raffleNumbers) {
        this.raffleNumbers = raffleNumbers;
    }

    public String getRaffleSortedNumbers() {
        return raffleSortedNumbers;
    }

    public void setRaffleSortedNumbers(String raffleSortedNumbers) {
        this.raffleSortedNumbers = raffleSortedNumbers;
    }

    public String generateRaffleNumbers(){
        return "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75";
    }
}
