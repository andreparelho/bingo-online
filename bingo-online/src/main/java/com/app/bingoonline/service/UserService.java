package com.app.bingoonline.service;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.model.request.CreateUserRequest;
import com.app.bingoonline.model.request.UserUpdateRequest;

import java.util.Optional;
import java.util.UUID;


public interface UserService {
    void createUser(CreateUserRequest createUserRequest);
    void updateUser(UUID id, UserUpdateRequest userUpdateRequest);
    Optional<UserEntity> findUserById(UUID uuid);
    void deleteUser(UUID userId);
}
