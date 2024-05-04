package com.app.bingoonline.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping
    public void createUser(){
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
