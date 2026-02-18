package org.calendarmanagement.exception;

import lombok.Getter;

@Getter
public class UserException extends CustomServiceException{

    public UserException(ErrorCode errorCode){
        super(errorCode);
    }
}
