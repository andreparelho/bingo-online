package com.app.bingoonline.shared;

import com.app.bingoonline.ticket.factory.impl.*;

import java.util.*;

public class TicketShared {
    public BFactoryImpl bFactoryImpl = new BFactoryImpl();
    public IFactoryImpl iFactoryImpl = new IFactoryImpl();
    public NFactoryImpl nFactoryImpl = new NFactoryImpl();
    public GFactoryImpl gFactoryImpl = new GFactoryImpl();
    public OFactoryImpl oFactoryImpl = new OFactoryImpl();

    public Map<String, List<Integer>> generateCardTicket(){
        Map<String, List<Integer>> ticket = new HashMap<>();
        ticket.put("b", this.bFactoryImpl.generateTicketNumbers());
        ticket.put("i", this.iFactoryImpl.generateTicketNumbers());
        ticket.put("n", this.nFactoryImpl.generateTicketNumbers());
        ticket.put("g", this.gFactoryImpl.generateTicketNumbers());
        ticket.put("o", this.oFactoryImpl.generateTicketNumbers());
        return ticket;
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
