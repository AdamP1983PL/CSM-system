package com.example.csmSystem.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/*
 * we use @ControllerAdvice to handle the exceptions globally
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
     *  we need to pass two parameters to this method: (TypeOfTheException exception, WebRequest webRequest)
     *  in order to get some details from webRequest;
     *
     * the @ExceptionHandler annotation is used to handle specific exceptions and sending
     * the custom response to the Client
     *
     * we use @ExceptionHandler to handle the specific exception
     * */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode("USER_NOT_FOUND")
                .build()
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleNameAlreadyExistsException(NameAlreadyExistException exception,
                                                                         WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode("TEST_NAME_ALREADY_EXISTS_IN_THE_DB")
                .build()
                , HttpStatus.BAD_REQUEST);
    }

    /*
     *  test Exception to handle all the other kinds of exceptions
     * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllTheOtherExceptions(Exception exception,
                                                                    WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .path(webRequest.getDescription(false))
                .errorCode("TEST_EXCEPTION_TO_HANDLE_ALL_THE_OTHER_EXCEPTIONS")
                .build()
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
