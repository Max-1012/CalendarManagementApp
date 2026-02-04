package org.calendarmanagement.dto;

import lombok.Getter;
import org.calendarmanagement.entity.Schedule;

@Getter
public class CreateCommentRequest {

    private String content;
    private String author;
    private String password;
}
