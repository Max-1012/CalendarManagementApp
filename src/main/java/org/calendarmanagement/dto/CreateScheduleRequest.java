package org.calendarmanagement.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    // 일정 제목
    private String title;
    // 일정 내용
    private String content;
    // 작성자명
    private String authorName;
    // 비밀번호
    private String password;
}
