package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.repository.crud.UserCrudRepository;
import com.app.bingoonline.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserCrudRepository userCrudRepository;

    public UserServiceImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return null;
    }
}
