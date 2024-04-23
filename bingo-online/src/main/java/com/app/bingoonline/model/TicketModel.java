package com.app.bingoonline.model;

import lombok.Builder;

@Builder
public class TicketModel {
    private Long contestNumberId;
    private String ticket;

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
