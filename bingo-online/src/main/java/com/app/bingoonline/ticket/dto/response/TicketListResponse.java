package com.app.bingoonline.ticket.dto.response;

import com.app.bingoonline.ticket.entity.TicketEntity;

import java.util.List;
import java.util.Map;

public record TicketListResponse(Map<Integer, List<TicketEntity>> ticketList){
}
