package com.app.bingoonline.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserPasswordInvalidException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserPasswordInvalidException(String message) {
        super(message);
    }
}