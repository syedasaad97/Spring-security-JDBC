package com.auth.assignment.aop;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        this(message, null);
    }

    public UserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);

    }
}

