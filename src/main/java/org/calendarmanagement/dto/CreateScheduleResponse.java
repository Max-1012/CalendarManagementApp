package org.calendarmanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {

    private final Long id;
    // 일정 제목
    private final String title;
    // 일정 내용
    private final String content;
    // 작성자명
    private final String author;
    // 작성일
    private final LocalDateTime createdDate;
    // 수정일
    private final LocalDateTime modifiedDate;

    public CreateScheduleResponse(Long id, String title, String content, String author, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
