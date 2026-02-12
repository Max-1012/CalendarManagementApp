package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.common.ExceptionHandler;
import org.calendarmanagement.dto.commentDto.request.CreateCommentRequest;
import org.calendarmanagement.dto.commentDto.response.CreateCommentResponse;
import org.calendarmanagement.dto.commentDto.response.GetCommentResponse;
import org.calendarmanagement.entity.Comment;
import org.calendarmanagement.repository.CommentRepository;
import org.calendarmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final ExceptionHandler exceptionHandler = new ExceptionHandler();


    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) throws Exception {
        exceptionHandler.validateCommentRequest(scheduleId, request, commentRepository, scheduleRepository);
        Comment comment = new Comment(scheduleId, request.getContent(), request.getAuthor(), request.getPassword());
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(savedComment.getId(), scheduleId, savedComment.getContent(),
                savedComment.getAuthor(), savedComment.getCreatedDate(),savedComment.getModifiedDate());
    }
    @Transactional(readOnly = true)
    public List<GetCommentResponse> getCommentsByScheduleId(Long scheduleId) {
        return commentRepository.findAll().stream().filter(comment -> comment.getScheduleId().equals(scheduleId))
                .map(comment -> new GetCommentResponse(comment.getId(), comment.getContent(), comment.getAuthor(),
                        comment.getCreatedDate(), comment.getModifiedDate())).toList();

    }
}
