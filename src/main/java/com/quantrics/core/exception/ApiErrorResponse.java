package com.quantrics.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private HttpStatus status;
    private String message;
    private String title;

}
