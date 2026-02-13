package org.calendarmanagement.dto.commentDto.response;

import lombok.Getter;
import org.calendarmanagement.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {

    private final Long id;
    private final Long scheduleId;
    private final String userName;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public CreateCommentResponse(Long id, Long scheduleId, String userName, String content,
                                 LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static CreateCommentResponse of(Comment comment){
        return new CreateCommentResponse(comment.getId(),comment.getSchedule().getId(),
                comment.getUser().getUserName(),comment.getContent(),
                comment.getCreatedDate(),comment.getModifiedDate());
    }
}
