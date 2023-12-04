package com.app.bingoonline.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;
import java.util.Set;

public interface TicketService {
    Map<String, Set<Integer>> generateTicket() throws JsonProcessingException;

    Map<String, Set<Integer>> generateTicketByContestId(int contestNumber) throws Exception;
}
