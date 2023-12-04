package com.app.bingoonline.repository;

import com.app.entity.ContestEntity;
import com.app.entity.TicketEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository {
    TicketEntity saveTicket(TicketEntity ticketEntity, ContestEntity contestEntity);
    List<TicketEntity> getAllTicketsByContest(ContestEntity contestEntity);
}
