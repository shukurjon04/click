package com.webapp.clicup.Exceptions;

public class UserEmailNotFoundException extends RuntimeException{
    public UserEmailNotFoundException(String message) {
        super(message);
    }
}
