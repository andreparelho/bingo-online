package com.app.bingoonline.service;

import com.app.bingoonline.entity.UserEntity;

public interface UserService {
    UserEntity findByUsername(String username);
}
