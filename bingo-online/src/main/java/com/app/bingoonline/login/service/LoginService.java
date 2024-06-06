package com.app.bingoonline.login.service;

import com.app.bingoonline.login.dto.request.LoginRequest;
import com.app.bingoonline.login.dto.response.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
