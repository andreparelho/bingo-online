package com.app.bingoonline.exception.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissingTokenException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public MissingTokenException(String message) {
        super(message);
    }
}
