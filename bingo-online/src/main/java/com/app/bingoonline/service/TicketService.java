package com.app.bingoonline.service;

import com.app.bingoonline.entity.TicketEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TicketService {
    Map<String, Set<Integer>> generateTicket() throws JsonProcessingException;

    Map<String, Set<Integer>> generateTicketByContestId(int contestNumber) throws Exception;

    String getRaffleNumber(int contestNumber);

    Map<Integer, List<TicketEntity>> getAllTicketsByContest(int contestNumber);
}

