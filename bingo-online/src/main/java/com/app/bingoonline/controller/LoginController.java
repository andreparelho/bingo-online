package com.app.bingoonline.controller;

import com.app.bingoonline.model.request.LoginRequest;
import com.app.bingoonline.model.response.LoginResponse;
import com.app.bingoonline.service.LoginService;
import com.app.bingoonline.util.LogUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
public class LoginController {
    private final LoginService loginService;
    private static final LogUtil logger = new LogUtil();

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        logger.createLog(getClass().getName(), "login", "login user request", null);

        LoginResponse response = this.loginService.login(loginRequest);

        logger.createLog("login", "login success", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public void logout(){
    }
}
