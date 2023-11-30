package com.app.entity;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long ticketNumber;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private ContestEntity contestEntity;

    private String ticket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public ContestEntity getContestEntity() {
        return contestEntity;
    }

    public void setContestEntity(ContestEntity contestEntity) {
        this.contestEntity = contestEntity;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}


