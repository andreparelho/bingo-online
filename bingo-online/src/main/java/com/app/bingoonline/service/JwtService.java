package com.app.bingoonline.service;

import com.app.bingoonline.entity.UserEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.util.Optional;

public interface JwtService {
    JwtClaimsSet getClaims(Optional<UserEntity> user);
    String getUserToken(JwtClaimsSet claims);
}
