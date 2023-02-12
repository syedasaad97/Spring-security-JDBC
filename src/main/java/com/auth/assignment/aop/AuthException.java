package com.auth.assignment.aop;

import lombok.Data;

@Data
public class AuthException extends RuntimeException {


    public AuthException(String message) {
        this(message, null);
    }

    public AuthException(String message, Throwable throwable) {
        super(message, throwable);

    }
}
