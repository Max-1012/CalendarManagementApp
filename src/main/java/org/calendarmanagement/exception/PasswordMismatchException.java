package org.calendarmanagement.exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends CustomServiceException {
    public PasswordMismatchException(String message) {

        super(HttpStatus.NOT_FOUND, message);
    }

}
