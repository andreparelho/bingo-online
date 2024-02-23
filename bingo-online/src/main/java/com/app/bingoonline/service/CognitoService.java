package com.app.bingoonline.service;

import com.app.bingoonline.model.UserRequest;

public interface CognitoService {
    boolean createUser(UserRequest userRequest);
}
