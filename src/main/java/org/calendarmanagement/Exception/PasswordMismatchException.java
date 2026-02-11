package org.calendarmanagement.Exception;

import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends ServiceException {
    public PasswordMismatchException(String message) {

        super(HttpStatus.NOT_FOUND, message);
    }

}
