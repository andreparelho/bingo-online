package com.app.bingoonline.model.ticket.card;

import org.springframework.stereotype.Component;

@Component
public class N extends Letter {
    @Override
    public int getQuantity(){
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