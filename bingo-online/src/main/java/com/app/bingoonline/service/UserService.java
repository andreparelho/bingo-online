package com.app.bingoonline.service;

import com.app.bingoonline.model.DTO.SignUpRequestDTO;
public interface UserService {
    boolean signUp(SignUpRequestDTO signUpRequestDTO);
}
