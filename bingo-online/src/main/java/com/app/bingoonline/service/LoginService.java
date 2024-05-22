package com.app.bingoonline.service;

import com.app.bingoonline.controller.request.LoginRequest;
import com.app.bingoonline.controller.response.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest loginRequest);
}
