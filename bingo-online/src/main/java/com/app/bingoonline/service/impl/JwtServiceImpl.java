package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.model.response.LoginResponse;
import com.app.bingoonline.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class JwtServiceImpl implements JwtService {
    private final JwtEncoder jwtEncoder;

    public JwtServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public JwtClaimsSet getClaims(Optional<UserEntity> user) {
        Instant now = Instant.now();
        long expiresIn = 300l;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("loginService.bingoApplication")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        return claims;
    }

    @Override
    public String getUserToken(JwtClaimsSet claims) {
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
