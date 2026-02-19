package org.calendarmanagement.dto.scheduleDto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record UpdateScheduleRequest (
    // 일정 제목
    @Pattern(regexp = ".*\\S.*", message = "공백만 입력할 수 없습니다.")
    @Size(max = 30, message = "일정 제목은 30자 이하여야 합니다.")
     String title,

    @Pattern(regexp = ".*\\S.*", message = "공백만 입력할 수 없습니다.")
    @Size(max = 100, message = "일정 내용은 100자 이하여야 합니다.")
     String content
){}
