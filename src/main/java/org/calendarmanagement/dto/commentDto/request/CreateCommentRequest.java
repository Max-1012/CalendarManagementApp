package org.calendarmanagement.dto.commentDto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CreateCommentRequest (
    @NotBlank(message = "댓글 내용은 필수값입니다.")
    @Size(max = 100, message = "댓글 내용은 100자 이하여야 합니다.")
    String content
){}