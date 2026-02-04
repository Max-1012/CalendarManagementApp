package org.calendarmanagement.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleWithCommentsResponse {
    // 일정 id
    private final Long id;
    // 일정 제목
    private final String title;
    // 일정 내용
    private final String content;
    // 작성자명
    private final String author;
    // 작성일
    private final LocalDateTime createdDate;
    // 수정일
    private final LocalDateTime modifiedDate;

    private final List<GetCommentResponse> commentList;

    public GetScheduleWithCommentsResponse(Long id, String title, String content, String author,
                                           LocalDateTime createdDate, LocalDateTime modifiedDate, List<GetCommentResponse> commentList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.commentList = commentList;
    }
}
