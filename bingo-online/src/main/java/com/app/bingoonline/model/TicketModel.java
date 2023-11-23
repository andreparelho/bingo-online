package com.app.bingoonline.model;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public abstract class TicketModel {
    public int getNumbers(){
        return 0;
    }

    public int[] generateTicketNumbers(){
        Random randomNumber = new Random();
        int [] numbersForLetter = new int[getNumbers()];

        for (int i = 0; i < getNumbers(); i++){
            numbersForLetter[i] = randomNumber.nextInt(0, 0);
        }
        return numbersForLetter;
    }
}
