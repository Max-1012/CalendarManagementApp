package org.calendarmanagement.Exception;

import org.springframework.http.HttpStatus;

public class NoSuchInstanceException extends ServiceException{
    public NoSuchInstanceException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
