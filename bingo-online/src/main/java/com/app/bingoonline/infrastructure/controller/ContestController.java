package com.app.bingoonline.infrastructure.controller;

import com.app.bingoonline.contest.dto.response.ContestResponseList;
import com.app.bingoonline.contest.dto.response.CreateContestResponse;
import com.app.bingoonline.ticket.dto.response.TicketListResponse;
import com.app.bingoonline.ticket.dto.response.TicketResponse;
import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.user.exception.UserNotFoundException;
import com.app.bingoonline.contest.service.ContestService;
import com.app.bingoonline.ticket.service.TicketService;
import com.app.bingoonline.user.service.UserService;
import com.app.bingoonline.infrastructure.util.LogUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.app.bingoonline.infrastructure.util.LogContants.*;

@RestController
@RequestMapping("/v1/contests")
public class ContestController {
    private static final LogUtil logger = new LogUtil();
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
        logger.createLog(CONTEST_CONTROLLER_CLASS, CONTROLLER_TYPE, CONTEST_GET_ALL_METHOD, CONTEST_INIT_METHOD_MSG, null);

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        ContestResponseList contestResponseList = this.contestService.getAllContests();

        logger.createLog(CONTROLLER_TYPE, CONTEST_GET_ALL_METHOD, CONTEST_FIN_METHOD_MSG, contestResponseList);
        return ResponseEntity.ok(contestResponseList);
    }

    @PostMapping()
    public ResponseEntity<CreateContestResponse> createContest(JwtAuthenticationToken authorizationHeader) {
        logger.createLog(CONTEST_CONTROLLER_CLASS, CONTROLLER_TYPE, CONTEST_CREATE_METHOD, CONTEST_INIT_METHOD_MSG,  Instant.now(),null);

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        CreateContestResponse createContestResponse = this.contestService.createContest();

        logger.createLog(CONTROLLER_TYPE, CONTEST_CREATE_METHOD, CONTEST_FIN_METHOD_MSG, Instant.now(), createContestResponse);
        return ResponseEntity.ok(createContestResponse);
    }

    @PostMapping("/{contest-number}/tickets")
    public ResponseEntity<TicketResponse> createTicket(@PathVariable("contest-number") int contestNumber,
                                                       JwtAuthenticationToken authorizationHeader) throws Exception {
        logger.createLog(CONTEST_CONTROLLER_CLASS, CONTROLLER_TYPE, CONTEST_CREATE_TICKET_METHOD, CONTEST_INIT_METHOD_MSG,  Instant.now(), null);

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        TicketResponse ticketResponse = this.ticketService.generateTicketByContestId(contestNumber);

        logger.createLog(CONTROLLER_TYPE, CONTEST_CREATE_METHOD, CONTEST_FIN_METHOD_MSG, Instant.now(), ticketResponse);
        return ResponseEntity.ok(ticketResponse);
    }

    @GetMapping("/{contest-number}/tickets")
    public ResponseEntity<TicketListResponse> getAllTicketsByContest(@PathVariable("contest-number") int contestNumber,
                                                                     JwtAuthenticationToken authorizationHeader) {
        logger.createLog(CONTEST_CONTROLLER_CLASS, CONTROLLER_TYPE, CONTEST_GET_ALL_TICKETS_METHOD, CONTEST_INIT_METHOD_MSG,  Instant.now(), null);

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        TicketListResponse ticketResponse = this.ticketService.getAllTicketsByContest(contestNumber);

        logger.createLog(CONTROLLER_TYPE, CONTEST_GET_ALL_TICKETS_METHOD, CONTEST_FIN_METHOD_MSG, Instant.now(), ticketResponse);
        return ResponseEntity.ok(ticketResponse);
    }
}
