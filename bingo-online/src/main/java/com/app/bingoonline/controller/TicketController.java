package com.app.bingoonline.controller;

import com.app.bingoonline.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    public final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/generate")
    public ResponseEntity<Map<String, int[]>> getTickets(){
        Map<String, int[]> tickets = this.ticketService.generateTicket();
        return ResponseEntity.ok(tickets);
    }
}
