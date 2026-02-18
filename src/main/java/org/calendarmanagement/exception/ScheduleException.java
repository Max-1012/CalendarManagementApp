package org.calendarmanagement.exception;

import lombok.Getter;

@Getter
public class ScheduleException extends CustomServiceException {

    public ScheduleException(ErrorCode errorCode){
        super(errorCode);
    }
}
