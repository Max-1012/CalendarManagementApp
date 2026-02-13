package org.calendarmanagement.dto.commentDto.response;

import org.calendarmanagement.entity.Comment;

import java.time.LocalDateTime;

public class UpdateCommentResponse {
    private final Long id;
    private final Long scheduleId;
    private final String userName;
    private final String content;
    private final LocalDateTime CreatedDate;
    private final LocalDateTime modifiedDate;

    public UpdateCommentResponse(Long id, Long scheduleId, String userName, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.content = content;
        CreatedDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
    public static UpdateCommentResponse of(Comment comment){
        return new UpdateCommentResponse(comment.getId(),comment.getSchedule().getId(),comment.getUser().getUserName(),
                comment.getContent(),comment.getCreatedDate(),comment.getModifiedDate());
    }
}
