package com.app.bingoonline.model;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.repository.ContestRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Raffle {

    private ContestRepository contestRepository;

    public Raffle() {
    }

    public Raffle (ContestRepository contestRepository){
        this.contestRepository = contestRepository;
    }

    public String createRandomRaffleNumber(){
        Random random = new Random();
        return String.valueOf(random.nextInt(1, 75));
    }

    public ContestEntity getAllRaffleNumbers(int contestNumber) {
        return this.contestRepository.findContestNumber(contestNumber);
    }

    public boolean verifyContest(String raffleNumbers){
        return raffleNumbers != null;
    }

    public String verifyNumbers(List<Integer> numbers, String raffleNumber){
        Integer raffleNumberInteger = this.changeRaffleStringToInteger(raffleNumber);
        for (Integer number : numbers){
            if (number.equals(raffleNumberInteger)){
                raffleNumber = this.createRandomRaffleNumber();
            }
        }
        return raffleNumber;
    }

    public List<String> updateRaffleList(String raffleNumbersDatabase, String raffleRandomNumber){
        List<String> updatedRaffleList = new ArrayList<>();
        updatedRaffleList.add(raffleNumbersDatabase);
        updatedRaffleList.add(raffleRandomNumber);
        return updatedRaffleList;
    }

    private Integer changeRaffleStringToInteger(String raffleNumber){
        return Integer.valueOf(raffleNumber);
    }
}
