package com.app.bingoonline.model;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class N extends TicketModel{
    @Override
    public int getNumbers(){
        return 4;
    }

    @Override
    public int getStart() {
        return 31;
    }

    @Override
    public int getEnd() {
        return 45;
    }
}
