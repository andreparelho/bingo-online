package com.app.bingoonline.ticket.dto.response;

import java.util.List;
import java.util.Map;

public record TicketResponse(String contest, Map<String, List<Integer>> ticketMap) {
}
