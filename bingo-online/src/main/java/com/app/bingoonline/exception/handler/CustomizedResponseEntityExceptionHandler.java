package com.app.bingoonline.exception.handler;

import com.app.bingoonline.exception.ExceptionResponse;
import com.app.bingoonline.exception.contest.ContestAlreadyExistsException;
import com.app.bingoonline.exception.contest.ContestNotFoundException;
import com.app.bingoonline.exception.contest.InvalidContestDetailsException;
import com.app.bingoonline.exception.raffle.RaffleNumberNotAvailableException;
import com.app.bingoonline.exception.ticket.InvalidTicketDetailsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContestAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse> handleContestAlreadyExistsException(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContestNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleContestNotFoundException(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidContestDetailsException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidContestDetailsException(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTicketDetailsException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidTicketDetailsException(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RaffleNumberNotAvailableException.class)
    public final ResponseEntity<ExceptionResponse> handleRaffleNumberNotAvailableException(Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
