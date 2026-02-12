package org.calendarmanagement.dto.scheduleDto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    // 일정 제목
    @NotBlank(message = "일정 제목은 필수값입니다.")
    @Max(30)
    private String title;
    // 일정 내용
    @NotBlank(message = "일정 내용은 필수값입니다.")
    @Max(100)
    private String content;
}
