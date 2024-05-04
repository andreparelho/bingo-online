package com.app.bingoonline.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping
    public void createUser(){
    }

    @GetMapping
    public void getUserByAdmin(){
    }

    @PutMapping
    public void updateUser(){
    }

    @DeleteMapping
    public void deleteUser(){
    }

    @DeleteMapping
    public void deleteUserByAdmin(){
    }

    @GetMapping
    public void getUsersByAdmin(){
    }
}
