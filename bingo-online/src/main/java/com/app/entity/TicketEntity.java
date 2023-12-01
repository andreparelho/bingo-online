package com.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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


