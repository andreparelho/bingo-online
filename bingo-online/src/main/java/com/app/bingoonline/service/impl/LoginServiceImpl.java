package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.user.UserPasswordInvalidException;
import com.app.bingoonline.model.request.LoginRequest;
import com.app.bingoonline.repository.UserRepository;
import com.app.bingoonline.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserEntity> findByUsername(LoginRequest loginRequest) {
        Optional<UserEntity> user = userRepository.findByUsername(loginRequest.username());

        if (isCorrectPassword(loginRequest, user)){
            return user;
        }

        throw new UserPasswordInvalidException("User password is invalid");
    }

    private Boolean isCorrectPassword(LoginRequest userLoginPassword, Optional<UserEntity> userPassword){
        return passwordEncoder.matches(userPassword.get().getPassword(), userLoginPassword.password());
    }
}
