package com.app.bingoonline.ticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Table(name = "tickets")
@Entity(name = "Ticket")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ticket_id")
    private UUID id;
    private Long contestNumberId;
    private String ticket;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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


