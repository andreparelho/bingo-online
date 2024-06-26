package com.app.bingoonline.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserPasswordInvalidException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    String message;
    int errorCode;

    public UserPasswordInvalidException(Throwable throwable,  String message, int errorCode) {
        super(message, throwable);
        this.message = message;
        this.errorCode = errorCode;
    }
}
