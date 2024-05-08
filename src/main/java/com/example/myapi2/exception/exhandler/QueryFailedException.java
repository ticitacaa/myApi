package com.example.myapi2.exception.exhandler;

public class QueryFailedException extends RuntimeException {

    public QueryFailedException() {
    }

    public QueryFailedException(String message) {
        super(message);
    }

    public QueryFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryFailedException(Throwable cause) {
        super(cause);
    }

    public QueryFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
