package com.app.bingoonline.infrastructure.controller;

import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.user.exception.UserNotFoundException;
import com.app.bingoonline.contest.dto.request.CreateUserRequest;
import com.app.bingoonline.user.dto.request.UserUpdateRequest;
import com.app.bingoonline.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest createUserRequest){
        this.userService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
                                           JwtAuthenticationToken authorizationHeader){

        Optional<UserEntity> user = this.userService.findUserById(UUID.fromString(authorizationHeader.getName()));

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        this.userService.updateUser(UUID.fromString(authorizationHeader.getName()), userUpdateRequest);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(JwtAuthenticationToken authorizationHeader){
        UUID userId = UUID.fromString(authorizationHeader.getName());
        Optional<UserEntity> user = this.userService.findUserById(userId);

        if (user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        this.userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
