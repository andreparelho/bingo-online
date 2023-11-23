package com.app.bingoonline.model;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class G extends TicketModel{
    @Override
    public int getNumbers() {
        return 5;
    }

    @Override
    public int getStart() {
        return 46;
    }

    @Override
    public int getEnd() {
        return 60;
    }
}
