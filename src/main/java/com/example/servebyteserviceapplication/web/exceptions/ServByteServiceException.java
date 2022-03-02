package com.example.servebyteserviceapplication.web.exceptions;

public class ServByteServiceException extends RuntimeException {

    public ServByteServiceException() {
    }

    public ServByteServiceException(String message) {
        super(message);
    }

    public ServByteServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServByteServiceException(Throwable cause) {
        super(cause);
    }

    public ServByteServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
