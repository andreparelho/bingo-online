package com.app.bingoonline.ticket.factory.impl;

import com.app.bingoonline.ticket.factory.TicketFactory;
import org.springframework.stereotype.Component;

@Component
public class NFactoryImpl extends TicketFactory {
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
