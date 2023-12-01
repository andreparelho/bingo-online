package com.app.bingoonline.controller;

import com.app.bingoonline.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    public final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/generate")
    public ResponseEntity<Map<String, Set<Integer>>> getTickets() throws JsonProcessingException {
        Map<String, Set<Integer>> tickets = this.ticketService.generateTicket();
        return ResponseEntity.ok(tickets);
    }
}
