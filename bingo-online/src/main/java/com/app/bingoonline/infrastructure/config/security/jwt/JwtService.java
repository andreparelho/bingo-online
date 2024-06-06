package com.app.bingoonline.infrastructure.config.security.jwt;

import com.app.bingoonline.user.entity.UserEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.util.Optional;

public interface JwtService {
    JwtClaimsSet getClaims(Optional<UserEntity> user);
    String getUserToken(JwtClaimsSet claims);
    String encodePassword(String password);
}
