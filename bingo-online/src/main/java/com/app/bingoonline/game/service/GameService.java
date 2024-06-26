package com.app.bingoonline.game.service;

import com.app.bingoonline.contest.entity.ContestEntity;
import com.app.bingoonline.ticket.entity.TicketEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface GameService {
    Optional<UUID> checkWinnerGameOne(ContestEntity contest) throws JsonProcessingException;
    ContestEntity checkWinnerGame(int contestNumber);
    void removeSortedNumberFromTickets(int sortedNumber, List<TicketEntity> ticketList, ContestEntity contest) throws JsonProcessingException;
}
