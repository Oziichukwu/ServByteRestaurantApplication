package com.example.servebyteserviceapplication.web.exceptions;

public class MealDoesNotExistException extends ServByteServiceException {

    public MealDoesNotExistException() {
    }

    public MealDoesNotExistException(String message) {
        super(message);
    }

    public MealDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public MealDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
