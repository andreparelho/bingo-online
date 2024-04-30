package com.app.bingoonline.model.dto;

import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
public class TicketDTO {
    private Long id;
    private Long contestNumberId;
    private String ticket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
