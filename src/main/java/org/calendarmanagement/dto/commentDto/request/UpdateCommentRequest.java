package org.calendarmanagement.dto.commentDto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    @Pattern(regexp = ".*\\S.*", message = "공백만 입력할 수 없습니다.")
    @Max(value = 100, message = "댓글 내용은 100자 이내로 작성해야 합니다.")
    private String content;
}
