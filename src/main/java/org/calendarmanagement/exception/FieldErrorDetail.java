package org.calendarmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorDetail {

    private String field;   // 필드명 (없으면 null 가능)
    private String message; // 에러 메시지
}
