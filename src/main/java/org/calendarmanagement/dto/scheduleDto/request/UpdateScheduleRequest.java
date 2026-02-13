package org.calendarmanagement.dto.scheduleDto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    // 일정 제목
    @Pattern(regexp = ".*\\S.*", message = "공백만 입력할 수 없습니다.")
    @Max(value = 30, message = "일정 제목은 30자 이내로 작성해야 합니다.")
    private String title;

    @Pattern(regexp = ".*\\S.*", message = "공백만 입력할 수 없습니다.")
    @Max(value = 100, message = "일정 내용은 100자 이내로 작성해야 합니다.")
    private String content;
}
