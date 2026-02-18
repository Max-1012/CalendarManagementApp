package org.calendarmanagement.exception;
import lombok.Getter;
@Getter
public class CustomServiceException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
