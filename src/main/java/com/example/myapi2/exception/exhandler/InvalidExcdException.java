package com.example.myapi2.exception.exhandler;

public class InvalidExcdException extends RuntimeException {

    public InvalidExcdException() {
    }

    public InvalidExcdException(String message) {
        super(message);
    }

    public InvalidExcdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidExcdException(Throwable cause) {
        super(cause);
    }

    public InvalidExcdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
