package com.auth.assignment.aop;

import com.auth.assignment.dto.ExceptionDto;
import com.auth.assignment.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApplicationExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ExceptionString = "Exception -> {}";

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionDto> authenticationException(AuthException exception) {
        final String message = exception.getMessage();
        log.error(ExceptionString, message, exception);
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDto> userNameNotFoundException(UsernameNotFoundException exception) {
        final String message = exception.getMessage();
        log.error(ExceptionString, message, exception);
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionDto> accessDeniedException(AccessDeniedException exception) {
        final String message = exception.getMessage();
        log.error(ExceptionString, message, exception);
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> userNotFoundException(UserNotFoundException exception) {
        final String message = exception.getMessage();
        log.error(ExceptionString, message, exception);
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDto> badCredentialsException(BadCredentialsException exception) {
        final String message = exception.getMessage();
        log.error(ExceptionString, message, exception);
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> genericException(Exception exception) {
        final String message = exception.getMessage();
        if (CommonUtils.isNotNull(exception.getCause()) && exception.getCause().getClass().equals(com.auth.assignment.aop.AuthException.class)) {
            return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.UNAUTHORIZED);
        }
        log.error(ExceptionString, message, exception);
        return new ResponseEntity<>(new ExceptionDto(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
