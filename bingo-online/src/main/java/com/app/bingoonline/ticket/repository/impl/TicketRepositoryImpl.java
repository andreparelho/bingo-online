package com.app.bingoonline.ticket.repository.impl;

import com.app.bingoonline.ticket.repository.TicketRepository;
import com.app.bingoonline.ticket.repository.crud.TicketCrudRepository;
import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.ticket.entity.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketRepositoryImpl implements TicketRepository {
    private final TicketCrudRepository ticketCrudRepository;

    @Autowired
    public TicketRepositoryImpl(TicketCrudRepository ticketCrudRepository) {
        this.ticketCrudRepository = ticketCrudRepository;
    }

    @Override
    public TicketEntity saveTicket(TicketEntity ticketEntity, ContestEntity contestEntity) {
        ticketEntity.setContestNumberId((long) contestEntity.getContestNumber());
        return this.ticketCrudRepository.save(ticketEntity);
    }

    @Override
    public List<TicketEntity> getAllTicketsByContest(int contestNumber) {
        return this.ticketCrudRepository.findByContestNumberId(contestNumber);
    }
}
