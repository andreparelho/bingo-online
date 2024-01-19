package com.app.bingoonline.controller;

import com.app.bingoonline.entity.TicketEntity;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/contests")
public class ContestController {
    public final TicketService ticketService;
    public final ContestService contestService;

    public ContestController(TicketService ticketService, ContestService contestService) {
        this.ticketService = ticketService;
        this.contestService = contestService;
    }

    @GetMapping()
    public ResponseEntity<List<Integer>> getAllContests() {
        List<Integer> allContests = this.contestService.getAllContests();
        return ResponseEntity.ok(allContests);
    }

    @PostMapping()
    public ResponseEntity<Map<String, Set<Integer>>> createContest() {
        Map<String, Set<Integer>> newContest = this.contestService.createContest();
        return ResponseEntity.ok(newContest);
    }

    @PostMapping("/{contest-number}/tickets")
    public ResponseEntity<Map<String, Set<Integer>>> createTicket(@PathVariable("contest-number") int contestNumber) throws Exception {
        Map<String, Set<Integer>> newTicket = this.ticketService.generateTicketByContestId(contestNumber);
        return ResponseEntity.ok(newTicket);
    }

    @GetMapping("/{contest-number}/tickets")
    public ResponseEntity<Map<Integer, List<TicketEntity>>> getAllTicketsByContest(@PathVariable("contest-number") int contestNumber) throws Exception {
        Map<Integer, List<TicketEntity>> allTickets = this.ticketService.getAllTicketsByContest(contestNumber);
        return ResponseEntity.ok(allTickets);
    }

    @GetMapping("/{contest-number}/numbers")
    public ResponseEntity<String> getRaffleNumber(@PathVariable("contest-number") int contestNumber){
        String raffleNumber = String.valueOf(this.ticketService.getRaffleNumber(contestNumber));
        return ResponseEntity.ok(raffleNumber);
    }
}
