package org.calendarmanagement.config;

import org.calendarmanagement.Exception.InvalidRequestException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // MovieNotFoundException 커스텀 에러 핸들링
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String> handleServiceException(InvalidRequestException ex) {
        return ResponseEntity
                .status(ex.getCause())
                .body(ex.getMessage());
    }
}