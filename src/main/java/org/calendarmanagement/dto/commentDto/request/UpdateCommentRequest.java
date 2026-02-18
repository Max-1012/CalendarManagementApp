package org.calendarmanagement.dto.commentDto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    @Pattern(regexp = ".*\\S.*", message = "공백만 입력할 수 없습니다.")
    @Size(max = 100, message = "댓글 내용은 100자 이하여야 합니다.")
    private String content;
}
