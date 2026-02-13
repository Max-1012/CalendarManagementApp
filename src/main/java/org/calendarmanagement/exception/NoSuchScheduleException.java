package org.calendarmanagement.exception;

import org.springframework.http.HttpStatus;

public class NoSuchScheduleException extends CustomServiceException {
    public NoSuchScheduleException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
