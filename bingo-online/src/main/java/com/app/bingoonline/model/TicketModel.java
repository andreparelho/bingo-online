package com.app.bingoonline.model;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public abstract class TicketModel {
    public abstract int getNumbers();

    public abstract int getStart();

    public abstract int getEnd();

    public int[] generateTicketNumbers(){
        Random randomNumber = new Random();
        int [] numbersForLetter = new int[getNumbers()];

        for (int i = 0; i < getNumbers(); i++){
            numbersForLetter[i] = randomNumber.nextInt(getStart(), getEnd());
        }
        return numbersForLetter;
    }
}
