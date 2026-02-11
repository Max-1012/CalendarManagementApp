package org.calendarmanagement.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    // 일정 제목
    private String title;
    // 일정 내용
    private String content;
}
