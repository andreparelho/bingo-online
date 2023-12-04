package com.app.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long contestNumberId;

    private String ticket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Long getContestNumberId() {
        return contestNumberId;
    }

    public void setContestNumberId(Long contestNumberId) {
        this.contestNumberId = contestNumberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketEntity that)) return false;
        return getId().equals(that.getId()) && getContestNumberId().equals(that.getContestNumberId()) && getTicket().equals(that.getTicket());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContestNumberId(), getTicket());
    }
}


