package org.calendarmanagement.Exception;
import org.springframework.http.HttpStatus;

public class InvalidInputException extends ServiceException {
    public InvalidInputException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}