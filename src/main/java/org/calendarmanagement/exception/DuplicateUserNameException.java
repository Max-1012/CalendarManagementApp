package org.calendarmanagement.exception;

import org.springframework.http.HttpStatus;

public class DuplicateUserNameException extends CustomServiceException {
    public DuplicateUserNameException(String message) {
            super(HttpStatus.BAD_REQUEST, message);
    }

}
