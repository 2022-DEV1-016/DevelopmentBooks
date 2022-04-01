package com.digitalstork.devbookskata.exception;

public class InvalidBookQuantityException extends RuntimeException{

    public InvalidBookQuantityException() {
        super();
    }

    public InvalidBookQuantityException(String message) {
        super(message);
    }
}
