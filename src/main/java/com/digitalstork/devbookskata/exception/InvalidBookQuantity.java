package com.digitalstork.devbookskata.exception;

public class InvalidBookQuantity extends RuntimeException{

    public InvalidBookQuantity() {
        super();
    }

    public InvalidBookQuantity(String message) {
        super(message);
    }
}
