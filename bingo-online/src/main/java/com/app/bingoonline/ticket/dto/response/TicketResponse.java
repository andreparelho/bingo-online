package com.app.bingoonline.ticket.dto.response;

import java.util.Map;
import java.util.Set;

public record TicketResponse(Map<String, Set<Integer>> ticket) {
}
