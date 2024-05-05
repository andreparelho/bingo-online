package com.app.bingoonline.service;

import com.app.bingoonline.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findByUsername(String username);
}
