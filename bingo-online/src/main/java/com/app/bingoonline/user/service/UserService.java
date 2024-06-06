package com.app.bingoonline.user.service;

import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.contest.dto.request.CreateUserRequest;
import com.app.bingoonline.user.dto.request.UserUpdateRequest;

import java.util.Optional;
import java.util.UUID;


public interface UserService {
    void createUser(CreateUserRequest createUserRequest);
    void updateUser(UUID id, UserUpdateRequest userUpdateRequest);
    Optional<UserEntity> findUserById(UUID uuid);
    void deleteUser(UUID userId);
}
