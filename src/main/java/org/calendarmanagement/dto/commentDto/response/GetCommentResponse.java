package org.calendarmanagement.dto.commentDto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final Long id;
    private final String content;
    private final String author;
    private final LocalDateTime CreatedDate;
    private final LocalDateTime modifiedDate;

    public GetCommentResponse(Long id, String content, String author, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.content = content;
        this.author = author;
        CreatedDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
