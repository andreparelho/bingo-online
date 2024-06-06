package com.app.bingoonline.ticket.dto;

import java.util.UUID;

public record TicketDTO (UUID id, Long contestNumberId, String ticket) {
}
