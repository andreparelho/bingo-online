package com.app.bingoonline.exception.contest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContestAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ContestAlreadyExistsException(String message) {
        super(message);
    }
}
