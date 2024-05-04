package com.app.bingoonline.model.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public class TicketDTO {
    private UUID id;
    private Long contestNumberId;
    private String ticket;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getContestNumberId() {
        return contestNumberId;
    }

    public void setContestNumberId(Long contestNumberId) {
        this.contestNumberId = contestNumberId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
