package com.app.bingoonline.service;

import com.app.bingoonline.model.request.LoginRequest;
import com.app.bingoonline.model.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
}
