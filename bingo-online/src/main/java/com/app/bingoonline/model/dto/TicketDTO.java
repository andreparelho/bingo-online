package com.app.bingoonline.model.dto;

import java.util.UUID;

public record TicketDTO (UUID id, Long contestNumberId, String ticket) {
}
