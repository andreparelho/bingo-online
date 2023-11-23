package com.app.bingoonline.model;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class I extends TicketModel{
    @Override
    public int getNumbers(){
        return 5;
    }

    @Override
    public int getStart() {
        return 16;
    }

    @Override
    public int getEnd() {
        return 30;
    }
}
