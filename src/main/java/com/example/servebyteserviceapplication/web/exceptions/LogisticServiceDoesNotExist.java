package com.example.servebyteserviceapplication.web.exceptions;

public class LogisticServiceDoesNotExist extends ServByteServiceException {
    public LogisticServiceDoesNotExist() {
    }

    public LogisticServiceDoesNotExist(String message) {
        super(message);
    }

    public LogisticServiceDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public LogisticServiceDoesNotExist(Throwable cause) {
        super(cause);
    }

    public LogisticServiceDoesNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
