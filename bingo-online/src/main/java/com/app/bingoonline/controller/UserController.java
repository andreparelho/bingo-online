package com.app.bingoonline.controller;

import com.app.bingoonline.model.request.CreateUserRequest;
import com.app.bingoonline.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/user-by-admin")
    public void getUserByAdmin(){
    }

    @PutMapping
    public void updateUser(){
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
