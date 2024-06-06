package com.app.bingoonline.ticket.factory.impl;

import com.app.bingoonline.ticket.factory.TicketFactory;
import org.springframework.stereotype.Component;

@Component
public class OFactoryImpl extends TicketFactory {
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
