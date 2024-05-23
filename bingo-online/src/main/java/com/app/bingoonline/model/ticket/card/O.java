package com.app.bingoonline.model.ticket.card;

import org.springframework.stereotype.Component;

@Component
public class O extends Letter {
    @Override
    public int getQuantity(){
        return 5;
    }

    @Override
    public int getStart() {
        return 61;
    }

    @Override
    public int getEnd() {
        return 75;
    }
}
