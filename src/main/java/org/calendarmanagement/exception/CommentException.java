package org.calendarmanagement.exception;

import lombok.Getter;

@Getter
public class CommentException extends CustomServiceException {

    public CommentException(ErrorCode errorCode){
        super(errorCode);
    }
}
