package com.app.bingoonline.ticket.service;

import com.app.bingoonline.ticket.dto.response.TicketListResponse;
import com.app.bingoonline.ticket.dto.response.TicketResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;
import java.util.Set;

public interface TicketService {
    Map<String, Set<Integer>> generateTicket() throws JsonProcessingException;

    TicketResponse generateTicketByContestId(int contestNumber) throws Exception;

    TicketListResponse getAllTicketsByContest(int contestNumber);
}

