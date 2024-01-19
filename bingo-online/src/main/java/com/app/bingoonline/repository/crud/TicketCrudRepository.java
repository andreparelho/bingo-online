package com.app.bingoonline.repository.crud;

import com.app.bingoonline.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketCrudRepository extends CrudRepository<TicketEntity, Long> {
    List<TicketEntity> findByContestNumberId(long contestNumberId);
}
