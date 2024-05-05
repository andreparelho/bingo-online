package com.app.bingoonline.repository.impl;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.repository.UserRepository;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.empty();
    }
}
