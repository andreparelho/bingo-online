package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.user.UserPasswordInvalidException;
import com.app.bingoonline.model.request.LoginRequest;
import com.app.bingoonline.model.response.LoginResponse;
import com.app.bingoonline.repository.UserRepository;
import com.app.bingoonline.service.JwtService;
import com.app.bingoonline.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        Optional<UserEntity> user = this.userRepository.findByUsername(loginRequest.username());

        if (!isCorrectPassword(loginRequest, user)){
            throw new UserPasswordInvalidException("User password is invalid");
        }

        JwtClaimsSet claims = this.jwtService.getClaims(user);
        String token = this.jwtService.getUserToken(claims);

        LoginResponse response = new LoginResponse(token, claims.getExpiresAt().toString());

        return ResponseEntity.ok(response);
    }

    private Boolean isCorrectPassword(LoginRequest userLoginPassword, Optional<UserEntity> userPassword){
        return this.passwordEncoder.matches(userPassword.get().getPassword(), userLoginPassword.password());
    }
}
