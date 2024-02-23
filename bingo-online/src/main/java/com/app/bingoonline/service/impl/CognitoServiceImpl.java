package com.app.bingoonline.service.impl;

import com.app.bingoonline.model.UserRequest;
import com.app.bingoonline.service.CognitoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

@Service
public class CognitoServiceImpl implements CognitoService {
    public final CognitoIdentityProviderClient cognitoIdentityProviderClient;
    Logger logger = LoggerFactory.getLogger(CognitoServiceImpl.class);

    public CognitoServiceImpl(CognitoIdentityProviderClient cognitoIdentityProviderClient) {
        this.cognitoIdentityProviderClient = cognitoIdentityProviderClient;
    }

    @Override
    public SignUpResponse createUser(UserRequest userRequest){
        logger.debug("UserRequest: {}", userRequest);

        SignUpResponse signUpResponse = null;

        AttributeType userAttributes = AttributeType.builder()
                .name("email")
                .value(userRequest.getEmail())
                .build();

        try {
            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(userAttributes)
                    .username(userRequest.getUserName())
                    .clientId(userRequest.getClientId())
                    .password(userRequest.getPassword())
                    .build();

            logger.debug("SignUpRequest: {}", signUpRequest);

            signUpResponse = cognitoIdentityProviderClient.signUp(signUpRequest);
        } catch (CognitoIdentityProviderException cognitoIdentityProviderException) {
            logger.info(cognitoIdentityProviderException.awsErrorDetails().errorMessage());
        }

        return signUpResponse;
    }
}
