package org.calendarmanagement.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedUserException extends CustomServiceException {
    public UnAuthorizedUserException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}