package com.app.bingoonline.mapper;

import com.app.bingoonline.entity.ContestEntity;
import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.model.ContestModel;
import com.app.bingoonline.model.TicketModel;

public class TicketMapper {
    public TicketModel entityToModel(TicketEntity ticketEntity){
        return TicketModel.builder()
                .ticket(ticketEntity.getTicket())
                .contestNumberId(ticketEntity.getContestNumberId())
                .build();
    }

    public TicketEntity modelToEntity(TicketModel ticketModel){
        return TicketEntity.builder()
                .ticket(ticketModel.getTicket())
                .contestNumberId(ticketModel.getContestNumberId())
                .build();
    }
}
