package org.calendarmanagement.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.calendarmanagement.dto.ApiResponse;
import org.calendarmanagement.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //  커스텀 에러 핸들링
    //  비즈니스 로직 예외 처리 (우리가 예상한 에러)
    @ExceptionHandler(CustomServiceException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomServiceException(CustomServiceException e, HttpServletRequest request) {
        log.warn("CustomServiceException : {}", e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        List<FieldErrorDetail> errors = List.of(
                new FieldErrorDetail(null, e.getMessage())
        );

        return ResponseEntity.status(errorCode.getStatus()).body(
                ApiResponse.fail(ErrorResponse.builder()
                        .status(errorCode.getStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .errors(errors)
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build())
        );

    }

    /**
     * 2. 유효성 검사 실패 처리 (@Valid)
     * DTO의 @NotBlank, @Pattern 조건 등을 만족하지 못했을 때 발생합니다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
        log.warn("ValidationException : {}", e.getMessage());

        List<FieldErrorDetail> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorDetail(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity.badRequest().body(
                ApiResponse.fail(ErrorResponse.builder()
                        .code(ErrorCode.INVALID_INPUT_VALUE.name())
                        .message("입력값이 올바르지 않습니다.")
                        .errors(errors)
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build())
        );
    }

    /**
     * 3. 예상치 못한 시스템 에러 (최후의 보루)
     * NullPointerException 등 우리가 예상하지 못한 에러가 발생했을 때 500 응답을 줍니다.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e, HttpServletRequest request) {
        log.error("Exception : {}", e.getMessage());
        List<FieldErrorDetail> errors = List.of(
                new FieldErrorDetail(null, e.getMessage())
        );
        return ResponseEntity.internalServerError().body(
                ApiResponse.fail(
                ErrorResponse.builder()
                        .code(ErrorCode.INTERNAL_SERVER_ERROR.name())
                        .message("입력값이 올바르지 않습니다.")
                        .errors(errors)
                        .path(request.getRequestURI())
                        .timestamp(LocalDateTime.now())
                        .build())
        );
    }
}

