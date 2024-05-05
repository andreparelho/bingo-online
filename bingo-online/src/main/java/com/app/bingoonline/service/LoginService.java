package com.app.bingoonline.service;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.model.request.LoginRequest;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(LoginRequest loginRequest);
}
