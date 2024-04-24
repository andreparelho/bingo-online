package com.app.bingoonline.mapper;

import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.model.dto.TicketDTO;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketDTO entityToDTO(TicketEntity ticketEntity){
        return TicketDTO.builder()
                .id(ticketEntity.getId())
                .ticket(ticketEntity.getTicket())
                .contestNumberId(ticketEntity.getContestNumberId())
                .build();
    }

    public TicketEntity DTOToEntity(TicketDTO ticketDTO){
        return TicketEntity.builder()
                .ticket(ticketDTO.getTicket())
                .contestNumberId(ticketDTO.getContestNumberId())
                .build();
    }
}
