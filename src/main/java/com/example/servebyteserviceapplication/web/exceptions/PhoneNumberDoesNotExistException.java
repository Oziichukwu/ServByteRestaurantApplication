package com.example.servebyteserviceapplication.web.exceptions;

public class PhoneNumberDoesNotExistException extends ServByteServiceException {
    public PhoneNumberDoesNotExistException() {
    }

    public PhoneNumberDoesNotExistException(String message) {
        super(message);
    }

    public PhoneNumberDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneNumberDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public PhoneNumberDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
