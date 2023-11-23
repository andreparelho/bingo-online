package com.app.bingoonline.model;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class B extends TicketModel{
    @Override
    public int getNumbers(){
        return 5;
    }

    @Override
    public int[] generateTicketNumbers(){
        Random randomNumber = new Random();
        int [] numbersForLetter = new int[getNumbers()];

        for (int i = 0; i < getNumbers(); i++){
            numbersForLetter[i] = randomNumber.nextInt(1, 15);
        }
        return numbersForLetter;
    }

}
