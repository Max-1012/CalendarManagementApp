package org.calendarmanagement.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends CustomServiceException {
    public DuplicateEmailException(String message) {
            super(HttpStatus.BAD_REQUEST, message);
    }

}
