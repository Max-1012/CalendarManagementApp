package org.calendarmanagement.dto.commentDto.request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private String content;
    private String author;
    private String password;
}
