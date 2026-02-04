package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.request.CreateCommentRequest;
import org.calendarmanagement.dto.response.CreateCommentResponse;
import org.calendarmanagement.dto.response.GetCommentResponse;
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
        boolean existence = scheduleRepository.existsById(scheduleId);
        if(!existence){
            throw new IllegalStateException("존재하지 않는 일정에 댓글을 달 수 없습니다.");
        }
        if(request.getAuthor()==null || request.getContent()==null || request.getPassword()==null){
            throw new IllegalStateException("댓글 내용, 작성자, 비밀번호는 필수입니다.");
        }
        // 댓글이 10개 이상이면 댓글 작성 불가
        long count = commentRepository.findAll().stream().filter(comment -> comment.getScheduleId().equals(scheduleId)).count();
        if(count>=10){
            throw new IllegalStateException("댓글 최대 개수 10개를 초과하여 댓글을 달 수 없습니다.");
        }
        // 댓글이 10개 이하인 경우 댓글 생성.
        Comment comment = new Comment(scheduleId, request.getContent(), request.getAuthor(), request.getPassword());
        Comment savedComment = commentRepository.save(comment);

        return new CreateCommentResponse(savedComment.getId(), scheduleId, savedComment.getContent(),
                savedComment.getAuthor(), savedComment.getCreatedDate(),savedComment.getModifiedDate());


    }

    public List<GetCommentResponse> getCommentsByScheduleId(Long scheduleId) {
        return commentRepository.findAll().stream().filter(comment -> comment.getScheduleId().equals(scheduleId))
                .map(comment -> new GetCommentResponse(comment.getId(), comment.getContent(), comment.getAuthor(),
                        comment.getCreatedDate(), comment.getModifiedDate())).toList();

    }
}
