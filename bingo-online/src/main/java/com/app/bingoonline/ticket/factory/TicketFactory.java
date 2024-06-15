package com.app.bingoonline.ticket.factory;

import java.util.*;

public abstract class TicketFactory {

    private final Random randomNumber = new Random();

    public abstract int getQuantity();

    public abstract int getStart();

    public abstract int getEnd();

    public List<Integer> generateTicketNumbers(){
        List<Integer> numbers = new ArrayList<>();

        if (getQuantity() == 0) throw new ArithmeticException();

        while (numbers.size() < getQuantity()) {
            int number = this.randomNumber.nextInt(getStart(), getEnd());
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        }

        return numbers;
    }
}
