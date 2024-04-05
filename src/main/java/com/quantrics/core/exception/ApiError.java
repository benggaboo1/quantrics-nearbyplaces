package com.quantrics.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiError extends RuntimeException {

    private HttpStatus status;
    private String message;
    private Throwable e;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}