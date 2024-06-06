package com.app.bingoonline.login.service.impl;

import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.user.exception.UserPasswordInvalidException;
import com.app.bingoonline.login.dto.request.LoginRequest;
import com.app.bingoonline.login.dto.response.LoginResponse;
import com.app.bingoonline.user.repository.UserRepository;
import com.app.bingoonline.infrastructure.config.security.jwt.JwtService;
import com.app.bingoonline.login.service.LoginService;
import com.app.bingoonline.infrastructure.util.LogUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private static final LogUtil logger = new LogUtil();

    public LoginServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        logger.createLog(getClass().getName(), "login", "login request", loginRequest.username());

        Optional<UserEntity> user = this.userRepository.findByUsername(loginRequest.username());

        boolean passwordMatcher = isCorrectPassword(loginRequest, user);

        if (!passwordMatcher){
            logger.createLogError("login", "password not matches", null);

            throw new UserPasswordInvalidException("User password is invalid");
        }

        JwtClaimsSet claims = this.jwtService.getClaims(user);
        String token = this.jwtService.getUserToken(claims);

        logger.createLog("login", "login success", null);

        return new LoginResponse(token, claims.getExpiresAt().toString());
    }

    private Boolean isCorrectPassword(LoginRequest userLoginPassword, Optional<UserEntity> userPassword){
        return this.passwordEncoder.matches(userLoginPassword.password(), userPassword.get().getPassword());
    }
}
