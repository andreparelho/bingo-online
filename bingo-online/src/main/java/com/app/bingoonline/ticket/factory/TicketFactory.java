package com.app.bingoonline.ticket.factory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class TicketFactory {

    private final Random randomNumber = new Random();

    public abstract int getQuantity();

    public abstract int getStart();

    public abstract int getEnd();

    public Set<Integer> generateTicketNumbers(){
        Set<Integer> numbers = new HashSet<>();

        if (getQuantity() == 0) throw new ArithmeticException();

        while (numbers.size() < getQuantity()){
            numbers.add(randomNumber.nextInt(getStart(), getEnd()));
        }
        return numbers;
    }
}
