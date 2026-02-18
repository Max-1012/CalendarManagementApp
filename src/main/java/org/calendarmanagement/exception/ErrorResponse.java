package org.calendarmanagement.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private final String status;
    private final String code;
    private final String message; // 대표 메시지 (요약)
    private final List<FieldErrorDetail> errors; // 상세 에러들
    private final String path;
    private final LocalDateTime timestamp;
}
