package com.app.bingoonline.user.service;

import com.app.bingoonline.login.dto.request.LoginRequest;
import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.contest.dto.request.CreateUserRequest;
import com.app.bingoonline.user.dto.request.UserUpdateRequest;
import com.app.bingoonline.user.exception.UserNotFoundException;
import com.app.bingoonline.user.exception.UserPasswordInvalidException;

import java.util.Optional;
import java.util.UUID;


public interface UserService {
    void createUser(CreateUserRequest createUserRequest);
    void updateUser(UUID id, UserUpdateRequest userUpdateRequest);
    Optional<UserEntity> findUserById(UUID uuid);
    void deleteUser(UUID userId);
    Boolean checkPasswordIsValid(LoginRequest userLoginPassword, Optional<UserEntity> userPassword) throws UserPasswordInvalidException;
}
