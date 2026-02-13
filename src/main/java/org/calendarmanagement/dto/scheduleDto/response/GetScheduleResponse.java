package org.calendarmanagement.dto.scheduleDto.response;

import org.calendarmanagement.entity.Schedule;
import java.time.LocalDateTime;

/**
 * @param userName     일정 작성자
 * @param title        일정 제목
 * @param content      일정 내용
 * @param createdDate  작성일
 * @param modifiedDate 수정일
 */
public record GetScheduleResponse(Long id, String userName, String title, String content, LocalDateTime createdDate,
                                  LocalDateTime modifiedDate) {
    public static GetScheduleResponse of(Schedule schedule) {
        return new GetScheduleResponse(schedule.getId(), schedule.getUser().getUserName(), schedule.getTitle(),
                schedule.getContent(), schedule.getCreatedDate(), schedule.getModifiedDate());
    }
}
