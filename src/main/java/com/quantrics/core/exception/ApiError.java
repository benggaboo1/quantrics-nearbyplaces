package com.quantrics.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiError extends RuntimeException {

    private String message;
    private Throwable e;

    public ApiError(String message) {
        this.message = message;
    }
}