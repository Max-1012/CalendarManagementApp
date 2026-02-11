package org.calendarmanagement.Exception;

import org.springframework.http.HttpStatus;

public class NoSuchUserException extends ServiceException {
    public NoSuchUserException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
