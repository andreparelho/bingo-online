package com.app.bingoonline.controller;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.user.UserNotFoundException;
import com.app.bingoonline.model.response.*;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.service.TicketService;
import com.app.bingoonline.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/contests")
public class ContestController {
    private final TicketService ticketService;
    private final ContestService contestService;
    private final UserService userService;

    public ContestController(TicketService ticketService, ContestService contestService, UserService userService) {
        this.ticketService = ticketService;
        this.contestService = contestService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<ContestResponseList> getAllContests(JwtAuthenticationToken authorizationHeader) {

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        ContestResponseList contestResponseList = this.contestService.getAllContests();
        return ResponseEntity.ok(contestResponseList);
    }

    @PostMapping()
    public ResponseEntity<CreateContestResponse> createContest(JwtAuthenticationToken authorizationHeader) {

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        CreateContestResponse createContestResponse = this.contestService.createContest();
        return ResponseEntity.ok(createContestResponse);
    }

    @PostMapping("/{contest-number}/tickets")
    public ResponseEntity<TicketResponse> createTicket(@PathVariable("contest-number") int contestNumber,
                                                       JwtAuthenticationToken authorizationHeader) throws Exception {
        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        TicketResponse ticketResponse = this.ticketService.generateTicketByContestId(contestNumber);
        return ResponseEntity.ok(ticketResponse);
    }

    @GetMapping("/{contest-number}/tickets")
    public ResponseEntity<TicketListResponse> getAllTicketsByContest(@PathVariable("contest-number") int contestNumber,
                                                                     JwtAuthenticationToken authorizationHeader) {

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        TicketListResponse allTickets = this.ticketService.getAllTicketsByContest(contestNumber);
        return ResponseEntity.ok(allTickets);
    }

    @GetMapping("/{contest-number}/numbers")
    public ResponseEntity<RaffleResponse> getRaffleNumber(@PathVariable("contest-number") int contestNumber,
                                                          JwtAuthenticationToken authorizationHeader) {

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        RaffleResponse raffleNumber = this.ticketService.getRaffleNumber(contestNumber);
        return ResponseEntity.ok(raffleNumber);
    }
}
