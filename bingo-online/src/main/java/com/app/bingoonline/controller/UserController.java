package com.app.bingoonline.controller;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.user.UserNotFoundException;
import com.app.bingoonline.model.request.CreateUserRequest;
import com.app.bingoonline.model.request.UserUpdateRequest;
import com.app.bingoonline.service.JwtService;
import com.app.bingoonline.service.UserService;
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
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest createUserRequest){
        this.userService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user-by-admin")
    public void getUserByAdmin(){
    }

    @PutMapping("/update")
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
    public void deleteUser(){
    }

    @DeleteMapping("/delete-user-by-admin")
    public void deleteUserByAdmin(){
    }

    @GetMapping("/by-admin")
    public void getUsersByAdmin(){
    }
}
