package com.app.bingoonline.ticket.factory.impl;

import com.app.bingoonline.ticket.factory.TicketFactory;
import org.springframework.stereotype.Component;

@Component
public class IFactoryImpl extends TicketFactory {
    @Override
    public int getQuantity(){
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
