package org.calendarmanagement.Exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends ServiceException {
    public InvalidRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);;
    }
}
