package com.app.bingoonline.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTicketDetailsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidTicketDetailsException(String message) {
        super(message);
    }
}
