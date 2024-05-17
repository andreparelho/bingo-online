package com.app.bingoonline.mapper;

import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.controller.dto.TicketDTO;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketDTO entityToDTO(TicketEntity ticketEntity){
        return new TicketDTO(
                ticketEntity.getId(),
                ticketEntity.getContestNumberId(),
                ticketEntity.getTicket()
                );
    }

    public TicketEntity DTOToEntity(TicketDTO ticketDTO){
        return TicketEntity.builder()
                .ticket(ticketDTO.ticket())
                .contestNumberId(ticketDTO.contestNumberId())
                .build();
    }
}
