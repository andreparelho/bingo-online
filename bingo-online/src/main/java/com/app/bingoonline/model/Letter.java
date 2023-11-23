package com.app.bingoonline.model;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public abstract class Letter {

    private final Random randomNumber = new Random();

    public abstract int getQuantity();

    public abstract int getStart();

    public abstract int getEnd();

    public Set<Integer> generateTicketNumbers(){
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < getQuantity()){
            numbers.add(randomNumber.nextInt(getStart(), getEnd()));
        }
        return numbers;
    }
}
