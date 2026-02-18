package org.calendarmanagement.dto.scheduleDto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    // 일정 제목
    @NotBlank(message = "일정 제목은 필수값입니다.")
    @Size(max = 30, message = "일정 제목은 30자 이하여야 합니다.")
    private String title;
    // 일정 내용
    @NotBlank(message = "일정 내용은 필수값입니다.")
    @Size(max = 100, message = "일정 내용은 100자 이하여야 합니다.")
    private String content;
}
