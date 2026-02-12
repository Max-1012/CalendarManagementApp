package org.calendarmanagement.config;

import org.calendarmanagement.Exception.CustomServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //  커스텀 에러 핸들링
    @ExceptionHandler(CustomServiceException.class)
    public ResponseEntity<String> handleServiceException(CustomServiceException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(ex.getMessage());
    }
}