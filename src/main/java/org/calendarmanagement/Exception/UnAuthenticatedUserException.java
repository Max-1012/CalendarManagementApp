package org.calendarmanagement.Exception;

import org.springframework.http.HttpStatus;

public class UnAuthenticatedUserException extends CustomServiceException {
    public UnAuthenticatedUserException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}