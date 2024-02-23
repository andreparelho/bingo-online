package com.app.bingoonline.service.impl;

import com.app.bingoonline.model.DTO.SignUpRequestDTO;
import com.app.bingoonline.model.UserRequest;
import com.app.bingoonline.service.CognitoService;
import com.app.bingoonline.service.UserService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

@Service
public class UserServiceImpl implements UserService {
    public CognitoService cognitoService;

    public UserServiceImpl(CognitoService cognitoService) {
        this.cognitoService = cognitoService;
    }

    @Override
    public SignUpResponse signUp(SignUpRequestDTO signUpRequestDTO){
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .clientId(signUpRequestDTO.getClientId())
                .username(signUpRequestDTO.getUsername())
                .password(signUpRequestDTO.getPassword())
                .build();

        UserRequest userRequest = new UserRequest();
        userRequest.setClientId(signUpRequest.clientId());
        userRequest.setUserName(signUpRequest.username());
        userRequest.setPassword(signUpRequest.password());

        SignUpResponse signUpResponse =  this.cognitoService.createUser(userRequest);
        return signUpResponse;
    }
}
