package com.app.bingoonline.ticket.service;

import com.app.bingoonline.ticket.dto.response.CreatedTicketResponse;
import com.app.bingoonline.ticket.dto.response.TicketListResponse;
import com.app.bingoonline.ticket.dto.response.TicketResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TicketService {
    CreatedTicketResponse generateTicket() throws JsonProcessingException;

    TicketResponse generateTicketByContestId(int contestNumber) throws Exception;

    TicketListResponse getAllTicketsByContest(int contestNumber);
}

