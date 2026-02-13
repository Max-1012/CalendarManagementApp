package org.calendarmanagement.dto.commentDto.response;

import lombok.Getter;
import org.calendarmanagement.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final Long id;
    private final Long scheduleId;
    private final String userName;
    private final String content;
    private final LocalDateTime CreatedDate;
    private final LocalDateTime modifiedDate;

    public GetCommentResponse(Long id, Long scheduleId, String userName, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.content = content;
        CreatedDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
    public static GetCommentResponse of(Comment comment){
        return new GetCommentResponse(comment.getId(),comment.getSchedule().getId(),comment.getUser().getUserName(),
                comment.getContent(),comment.getCreatedDate(),comment.getModifiedDate());
    }
}
