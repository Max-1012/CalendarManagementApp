package org.calendarmanagement.dto.commentDto.response;

import org.calendarmanagement.entity.Comment;
import java.time.LocalDateTime;

public record GetCommentResponse (
  Long id,
  Long scheduleId,
  String userName,
  String content,
  LocalDateTime createdDate,
  LocalDateTime modifiedDate
){
    public static GetCommentResponse of(Comment comment){
        return new GetCommentResponse(comment.getId(),comment.getSchedule().getId(),comment.getUser().getUserName(),
                comment.getContent(),comment.getCreatedDate(),comment.getModifiedDate());
    }
}
