package com.app.bingoonline.ticket.repository.crud;

import com.app.bingoonline.ticket.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TicketCrudRepository extends CrudRepository<TicketEntity, UUID> {
    List<TicketEntity> findByContestNumberId(long contestNumberId);
}
