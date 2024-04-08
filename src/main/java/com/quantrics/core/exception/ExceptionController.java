package com.quantrics.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.quantrics.core.constants.QuantricsConstants.TITLE_ERROR;

@ControllerAdvice
public class ExceptionController {

    /**
     * Handler for ApiError Exception
     **/
    @ExceptionHandler({ ApiError.class })
    public ResponseEntity<Object> handleApiException(ApiError e) {

        return new ResponseEntity<>(new ApiErrorResponse(e.getMessage(), TITLE_ERROR),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
