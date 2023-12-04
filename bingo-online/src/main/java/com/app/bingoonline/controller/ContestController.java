package com.app.bingoonline.controller;

import com.app.bingoonline.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/contest")
public class ContestController {
    public final TicketService ticketService;

    public ContestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket")
    public ResponseEntity<Map<String, Set<Integer>>> createTicket() throws JsonProcessingException {
        Map<String, Set<Integer>> tickets = this.ticketService.generateTicket();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/{contest-number}/ticket")
    public ResponseEntity<Map<String, Set<Integer>>> createTicketAndContest(@PathVariable("contest-number") int contestNumber) throws Exception {
        Map<String, Set<Integer>> tickets = this.ticketService.generateTicketByContestId(contestNumber);
        return ResponseEntity.ok(tickets);
    }
}
