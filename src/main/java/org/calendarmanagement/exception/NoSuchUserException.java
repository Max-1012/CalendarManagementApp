package org.calendarmanagement.exception;

import org.springframework.http.HttpStatus;

public class NoSuchUserException extends CustomServiceException {
    public NoSuchUserException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
