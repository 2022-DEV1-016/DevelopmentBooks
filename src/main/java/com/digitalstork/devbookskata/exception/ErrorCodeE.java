package com.digitalstork.devbookskata.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeE {
    BOOK_NOT_FOUND("Book Not Found");

    private final String errorMsg;
}
