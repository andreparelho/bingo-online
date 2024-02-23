package com.app.bingoonline.service;

import com.app.bingoonline.model.UserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpResponse;

public interface CognitoService {
    SignUpResponse createUser(UserRequest userRequest);
}
