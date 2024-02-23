package com.app.bingoonline.service;

import com.app.bingoonline.model.DTO.SignUpRequestDTO;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

public interface UserService {
    SignUpResponse signUp(SignUpRequestDTO signUpRequestDTO);
}
