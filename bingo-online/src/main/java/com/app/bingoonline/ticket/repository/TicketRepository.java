package com.app.bingoonline.ticket.repository;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.ticket.entity.TicketEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository {
    TicketEntity saveTicket(TicketEntity ticketEntity, ContestEntity contestEntity);
    List<TicketEntity> getAllTicketsByContest(int contestNumber);
}
