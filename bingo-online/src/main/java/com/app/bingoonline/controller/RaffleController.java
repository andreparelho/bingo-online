package com.app.bingoonline.controller;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.user.UserNotFoundException;
import com.app.bingoonline.model.response.RaffleResponse;
import com.app.bingoonline.service.RaffleService;
import com.app.bingoonline.service.UserService;
import com.app.bingoonline.util.LogUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.app.bingoonline.util.LogContants.*;

@RestController
@RequestMapping("/v1/raffle")
public class RaffleController {
    private static final LogUtil logger = new LogUtil();
    private final RaffleService raffleService;
    private final UserService userService;

    public RaffleController(RaffleService raffleService, UserService userService) {
        this.raffleService = raffleService;
        this.userService = userService;
    }

    @GetMapping("/{contest-number}/numbers")
    public ResponseEntity<RaffleResponse> getRaffleNumber(@PathVariable("contest-number") int contestNumber,
                                                          JwtAuthenticationToken authorizationHeader) {
        logger.createLog(CONTEST_CONTROLLER_CLASS, CONTROLLER_TYPE, CONTEST_GET_RAFFLE_METHOD, CONTEST_INIT_METHOD_MSG,  Instant.now(), null);

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        RaffleResponse raffleResponse = this.raffleService.getRaffleNumber(contestNumber);

        logger.createLog(CONTROLLER_TYPE, CONTEST_GET_RAFFLE_METHOD, CONTEST_FIN_METHOD_MSG, Instant.now(), raffleResponse);
        return ResponseEntity.ok(raffleResponse);
    }
}
