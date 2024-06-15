package com.app.bingoonline.ticket.dto.response;

import java.util.List;
import java.util.Map;

public record CreatedTicketResponse(String contest, Map<String, List<Integer>> ticketMap) {

}
