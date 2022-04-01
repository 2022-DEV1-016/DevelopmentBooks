package com.digitalstork.devbookskata.exception;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private HttpStatus status;
    private String errorCode;
    private String errorMsg;

}
