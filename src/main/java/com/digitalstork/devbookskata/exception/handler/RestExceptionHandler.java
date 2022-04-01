package com.digitalstork.devbookskata.exception.handler;

import com.digitalstork.devbookskata.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorCode(ErrorCodeE.BOOK_NOT_FOUND.name())
                .errorMsg(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(NoAvailableBooksException.class)
    public ResponseEntity<ErrorResponse> handleNoAvailableBooksException(NoAvailableBooksException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorCode(ErrorCodeE.BOOK_OUT_OF_STOCK.name())
                .errorMsg(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(InvalidBookQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBookQuantityException(InvalidBookQuantityException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorCode(ErrorCodeE.INVALID_QUANTITY_PARAM.name())
                .errorMsg(ex.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);

    }
}