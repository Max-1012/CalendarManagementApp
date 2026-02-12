package org.calendarmanagement.Exception;

import org.springframework.http.HttpStatus;

public class NoSuchCommentException extends CustomServiceException {
    public NoSuchCommentException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
