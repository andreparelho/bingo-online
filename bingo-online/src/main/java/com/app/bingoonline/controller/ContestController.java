package com.app.bingoonline.controller;

import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        Map<String, Set<Integer>> tickets = this.ticketService.generateTicketByContestId(contestNumber);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{contest-number}/tickets")
    public ResponseEntity<Map<Integer, List<String>>> getAllTicketsByContest(@PathVariable("contest-number") int contestNumber) throws Exception {
       /* CRIAR UMA SERVICE PARA TRAZER TODOS OS TICKETS BASEADO NO ID DO CONTEST, A IDEIA INICAL E UM MAP COM NUMERO DO CONTEST E UMA LISTA DE TICKETS */
        Map<Integer, List<String>> allTickets = new HashMap<>();
        List<String> tickets = new ArrayList<>();
        tickets.add("{\"b\":[2,6,9,12,14],\"g\":[48,51,59,46,47],\"i\":[21,22,25,28,29],\"Contest\":[9867],\"n\":[37,38,42,31],\"o\":[65,71,72,74,61]}");
        tickets.add("{\"b\":[1,9,11,12,13],\"g\":[48,49,52,55,58],\"i\":[16,18,21,24,25],\"Contest\":[1151],\"n\":[32,36,39,40],\"o\":[65,66,72,73,74]}");
        tickets.add("{\"b\":[7,10,11,12,14],\"g\":[52,54,55,58,46],\"i\":[16,18,19,23,25],\"Contest\":[1832],\"n\":[38,41,43,31],\"o\":[64,65,69,72,62]}");
        allTickets.put(contestNumber, tickets);
        return ResponseEntity.ok(allTickets);
    }

    @GetMapping("/{contest-number}/numbers")
    public ResponseEntity<String> getRaffleNumber(@PathVariable("contest-number") int contestNumber){
        String raffleNumber = String.valueOf(this.ticketService.getRaffleNumber(contestNumber));
        return ResponseEntity.ok(raffleNumber);
    }
}
