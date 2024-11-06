package com.pontoapp.pontoapp.exceptions;

public class UserServiceException extends RuntimeException {

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserServiceException(String message) {
        super(message);
    }
}
