package com.app.bingoonline.repository.impl;

import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.repository.crud.TicketCrudRepository;
import com.app.entity.ContestEntity;
import com.app.entity.TicketEntity;
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
        ticketEntity.setContestEntity(contestEntity);
        return this.ticketCrudRepository.save(ticketEntity);
    }

    @Override
    public List<TicketEntity> getAllTicketsByContest(ContestEntity contestEntity) {
        return null;
    }
}
