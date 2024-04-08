package com.quantrics.core.exception;

public class WikipediaQueryRemoteException extends ApiError {

    public WikipediaQueryRemoteException(String message, Throwable e) {
        super(message, e);
    }

    public WikipediaQueryRemoteException(String message) {
        super(message);
    }
}
