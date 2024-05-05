package com.app.bingoonline.controller;

import com.app.bingoonline.model.request.LoginRequest;
import com.app.bingoonline.model.response.LoginResponse;
import com.app.bingoonline.repository.UserRepository;
import com.app.bingoonline.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return this.loginService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout(){
    }
}
