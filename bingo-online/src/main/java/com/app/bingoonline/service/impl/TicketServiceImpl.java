package com.app.bingoonline.service.impl;

import com.app.bingoonline.model.extds.*;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    private final B b;
    public final I i;
    public final N n;
    public final G g;
    public final O o;
    private final ContestService contestService;

    @Autowired
    public TicketServiceImpl(B b, I i, N n, G g, O o, ContestService contestService) {
        this.b = b;
        this.i = i;
        this.n = n;
        this.g = g;
        this.o = o;
        this.contestService = contestService;
    }

    @Override
    public Map<String, Set<Integer>> generateTicket() {
        Map<String, Set<Integer>> ticket = new HashMap<>();
        ticket.put("b", this.b.generateTicketNumbers());
        ticket.put("i", this.i.generateTicketNumbers());
        ticket.put("n", this.n.generateTicketNumbers());
        ticket.put("g", this.g.generateTicketNumbers());
        ticket.put("o", this.o.generateTicketNumbers());

        int contestNumber = this.contestService.generateContestNumber();
        this.contestService.createContest(contestNumber);




        return ticket;
    }
}
