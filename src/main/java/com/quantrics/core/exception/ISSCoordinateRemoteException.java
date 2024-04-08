package com.quantrics.core.exception;

import org.springframework.http.HttpStatus;

public class ISSCoordinateRemoteException extends ApiError {

    public ISSCoordinateRemoteException(String message, Throwable e) {
        super(message, e);
    }

    public ISSCoordinateRemoteException(String message) {
        super(message);
    }
}
