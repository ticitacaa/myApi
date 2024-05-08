package com.example.myapi2.exception.exhandler;

public class InvalidOverExcdException extends RuntimeException {

    public InvalidOverExcdException() {
    }

    public InvalidOverExcdException(String message) {
        super(message);
    }

    public InvalidOverExcdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOverExcdException(Throwable cause) {
        super(cause);
    }

    public InvalidOverExcdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
