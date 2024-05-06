package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.token.FailedToCreateTokenException;
import com.app.bingoonline.service.JwtService;
import com.app.bingoonline.util.LogUtil;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
public class JwtServiceImpl implements JwtService {
    private final JwtEncoder jwtEncoder;
    private static final LogUtil logger = new LogUtil();

    public JwtServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public JwtClaimsSet getClaims(Optional<UserEntity> user) {
        logger.createLog(getClass().getSimpleName(), "getClaims", "getting claims from user " + user.get().getUsername(), null);

        Instant now = Instant.now();
        long expiresIn = 300l;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("jwtService.bingoApplication")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        logger.createLog("getClaims","claims created at " + claims.getIssuedAt(), claims);

        return claims;
    }

    @Override
    public String getUserToken(JwtClaimsSet claims) {
        logger.createLog(getClass().getSimpleName(), "getUserToken", "getting token for user", null);

        try {
            logger.createLog("getUserToken", "creating token", null);

            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            logger.createLog("getUserToken", "token created with success", token);

            return token;
        } catch (RuntimeException exception) {
            logger.createLogError("getUserToken", exception.getMessage(), null);

            throw new FailedToCreateTokenException("Failed to create token");
        }
    }
}
