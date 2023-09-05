package com.example.csmSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/* notes for myself:
 * we use @ControllerAdvice to handle the exceptions globally
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    /* notes for myself:
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
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleNameAlreadyExistsException(NameAlreadyExistException exception,
                                                                         WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "TEST_NAME_ALREADY_EXISTS_IN_THE_DB"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /* notes for myself:
    *  test Exception to handle all the other kinds of exceptions
    * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllTheOtherExceptions(Exception exception,
                                                                    WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "TEST_EXCEPTION_TO_HANDLE_ALL_THE_OTHER_EXCEPTIONS"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
