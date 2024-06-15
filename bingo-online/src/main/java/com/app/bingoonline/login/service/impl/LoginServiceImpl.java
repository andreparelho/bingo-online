package com.app.bingoonline.login.service.impl;

import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.user.exception.UserPasswordInvalidException;
import com.app.bingoonline.login.dto.request.LoginRequest;
import com.app.bingoonline.login.dto.response.LoginResponse;
import com.app.bingoonline.user.repository.UserRepository;
import com.app.bingoonline.infrastructure.config.security.jwt.JwtService;
import com.app.bingoonline.login.service.LoginService;
import com.app.bingoonline.infrastructure.util.LogUtil;
import com.app.bingoonline.user.service.UserService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.app.bingoonline.user.constant.UserConstant.*;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private static final LogUtil logger = new LogUtil();

    public LoginServiceImpl(UserRepository userRepository, UserService userService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            logger.createLog(getClass().getName(), "login", "login request", loginRequest.username());

            Optional<UserEntity> user = this.userRepository.findByUsername(loginRequest.username());

            this.userService.checkPasswordIsValid(loginRequest, user);

            JwtClaimsSet claims = this.jwtService.getClaims(user);
            String token = this.jwtService.getUserToken(claims);

            logger.createLog("login", "login success", null);

            return new LoginResponse(token, claims.getExpiresAt().toString());
        } catch (UserPasswordInvalidException exception) {
            logger.createLogError("login", "password not matches", exception.getMessage());
            throw new UserPasswordInvalidException(exception, exception.getMessage(), 400);
        }
    }
}
