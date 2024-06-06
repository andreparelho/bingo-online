package com.app.bingoonline.infrastructure.config.security.jwt.impl;

import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.login.exception.token.FailedToCreateTokenException;
import com.app.bingoonline.infrastructure.config.security.jwt.JwtService;
import com.app.bingoonline.infrastructure.util.LogUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
public class JwtServiceImpl implements JwtService {
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final LogUtil logger = new LogUtil();
    public JwtServiceImpl(JwtEncoder jwtEncoder, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtClaimsSet getClaims(Optional<UserEntity> user) {
        logger.createLog(getClass().getName(), "getClaims", "getting claims from user " + user.get().getUsername(), null);

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
        logger.createLog(getClass().getName(), "getUserToken", "getting token for user", null);

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

    @Override
    public String encodePassword(String password) {
        logger.createLog(getClass().getSimpleName(), "encodePassword", "password encoding", null);
        return this.passwordEncoder.encode(password);
    }
}
