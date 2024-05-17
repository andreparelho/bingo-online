package com.app.bingoonline.controller.dto;

import java.util.UUID;

public record TicketDTO (UUID id, Long contestNumberId, String ticket) {
}
