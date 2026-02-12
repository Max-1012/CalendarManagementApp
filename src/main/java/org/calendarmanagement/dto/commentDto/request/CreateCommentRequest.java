package org.calendarmanagement.dto.commentDto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotBlank(message = "댓글 내용은 필수값입니다.")
    @Max(100)
    private String content;
}
