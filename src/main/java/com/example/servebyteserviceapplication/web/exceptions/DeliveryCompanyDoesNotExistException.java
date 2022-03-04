package com.example.servebyteserviceapplication.web.exceptions;

public class DeliveryCompanyDoesNotExistException extends ServByteServiceException {
    public DeliveryCompanyDoesNotExistException() {
    }

    public DeliveryCompanyDoesNotExistException(String message) {
        super(message);
    }

    public DeliveryCompanyDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeliveryCompanyDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public DeliveryCompanyDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
