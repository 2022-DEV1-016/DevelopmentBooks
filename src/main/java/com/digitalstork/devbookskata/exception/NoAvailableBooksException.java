package com.digitalstork.devbookskata.exception;

public class NoAvailableBooksException extends RuntimeException{

    public NoAvailableBooksException() {
        this("There is no available books in stock");
    }

    public NoAvailableBooksException(String message) {
        super(message);
    }
}
