package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.CreateCommentRequest;
import org.calendarmanagement.dto.CreateCommentResponse;
import org.calendarmanagement.entity.Comment;
import org.calendarmanagement.entity.Schedule;
import org.calendarmanagement.repository.CommentRepository;
import org.calendarmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;


    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정에 댓글을 달 수 없습니다.")
        );
        if(request.getAuthor()==null || request.getContent()==null || request.getPassword()==null){
            throw new IllegalStateException("댓글 내용, 작성자, 비밀번호는 필수입니다.");
        }
        // 댓글이 10개 이상이면 댓글 작성 불가
        if(schedule.getCommentCount()>=10){
            throw new IllegalStateException("댓글 최대 개수 10개를 초과하여 댓글을 달 수 없습니다.");
        }
        // 댓글이 10개 이하인 경우 댓글 생성.
        Comment comment = new Comment(schedule, request.getContent(), request.getAuthor(), request.getPassword());
        Comment savedComment = commentRepository.save(comment);
        // 일정의 댓글 리스트에 생성한 댓글 추가
        schedule.addComment(savedComment);

        return new CreateCommentResponse(savedComment.getId(), schedule.getId(), savedComment.getContent(),
                savedComment.getAuthor(), savedComment.getCreatedDate(),savedComment.getModifiedDate());


    }
}
