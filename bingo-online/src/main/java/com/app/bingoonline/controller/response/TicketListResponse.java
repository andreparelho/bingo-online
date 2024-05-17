package com.app.bingoonline.controller.response;

import com.app.bingoonline.entity.TicketEntity;

import java.util.List;
import java.util.Map;

public record TicketListResponse(Map<Integer, List<TicketEntity>> ticketList){
}
