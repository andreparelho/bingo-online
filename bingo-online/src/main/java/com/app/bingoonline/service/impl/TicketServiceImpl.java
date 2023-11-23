package com.app.bingoonline.service.impl;

import com.app.bingoonline.model.*;
import com.app.bingoonline.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    public final B b;
    public final I i;
    public final N n;
    public final G g;
    public final O o;

    public TicketServiceImpl(B b, I i, N n, G g, O o) {
        this.b = b;
        this.i = i;
        this.n = n;
        this.g = g;
        this.o = o;
    }

    @Override
    public Map<String, int[]> generateTicket() {
        Map<String, int[]> ticket = new HashMap<>();
        ticket.put("b", this.b.generateTicketNumbers());
        ticket.put("i", this.i.generateTicketNumbers());
        ticket.put("n", this.n.generateTicketNumbers());
        ticket.put("g", this.g.generateTicketNumbers());
        ticket.put("o", this.o.generateTicketNumbers());

        return ticket;
    }
}
