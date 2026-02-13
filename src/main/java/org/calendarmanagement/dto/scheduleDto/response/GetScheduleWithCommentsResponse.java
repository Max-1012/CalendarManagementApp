package org.calendarmanagement.dto.scheduleDto.response;

import org.calendarmanagement.dto.commentDto.response.GetCommentResponse;
import org.calendarmanagement.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @param id           일정 id
 * @param userName     작성자명
 * @param title        일정 제목
 * @param content      일정 내용
 * @param createdDate  작성일
 * @param modifiedDate 수정일
 */
public record GetScheduleWithCommentsResponse(Long id, String userName, String title, String content,
                                              LocalDateTime createdDate, LocalDateTime modifiedDate,
                                              List<GetCommentResponse> commentList) {
    public static GetScheduleWithCommentsResponse from(Schedule schedule, List<GetCommentResponse> commentList) {
        return new GetScheduleWithCommentsResponse(schedule.getId(), schedule.getUser().getUserName(), schedule.getTitle(),
                schedule.getContent(), schedule.getCreatedDate(), schedule.getModifiedDate(), commentList);
    }
}
