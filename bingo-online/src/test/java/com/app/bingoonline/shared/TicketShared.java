package com.app.bingoonline.shared;

import com.app.bingoonline.model.ticket.card.*;

import java.util.*;

public class TicketShared {
    public B b = new B();
    public I i = new I();
    public N n = new N();
    public G g = new G();
    public O o = new O();

    public Map<String, Set<Integer>> generateCardTicket(){
        Map<String, Set<Integer>> ticket = new HashMap<>();
        ticket.put("b", this.b.generateTicketNumbers());
        ticket.put("i", this.i.generateTicketNumbers());
        ticket.put("n", this.n.generateTicketNumbers());
        ticket.put("g", this.g.generateTicketNumbers());
        ticket.put("o", this.o.generateTicketNumbers());
        return  ticket;
    }

    public Map<String, String> convertStringToMap(String data) {
        Map<String, String> map = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(data, " ");

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            String[] keyValue = token.split("=");
            map.put(keyValue[0], keyValue[1]);
        }

        return map;
    }
}
