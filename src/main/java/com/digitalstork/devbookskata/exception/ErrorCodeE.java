package com.digitalstork.devbookskata.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeE {
    BOOK_NOT_FOUND("Book Not Found"),
    BOOK_OUT_OF_STOCK("Book out of stock");

    private final String errorMsg;
}
