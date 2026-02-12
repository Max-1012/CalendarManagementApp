package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.Exception.NoSuchCommentException;
import org.calendarmanagement.Exception.UnAuthorizedUserException;
import org.calendarmanagement.dto.commentDto.request.CreateCommentRequest;
import org.calendarmanagement.dto.commentDto.request.UpdateCommentRequest;
import org.calendarmanagement.dto.commentDto.response.CreateCommentResponse;
import org.calendarmanagement.dto.commentDto.response.GetCommentResponse;
import org.calendarmanagement.dto.commentDto.response.UpdateCommentResponse;
import org.calendarmanagement.dto.userDto.SessionUser;
import org.calendarmanagement.entity.Comment;
import org.calendarmanagement.entity.Schedule;
import org.calendarmanagement.entity.User;
import org.calendarmanagement.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleService scheduleService;
    private final UserService userService;

    // 댓글 생성
    @Transactional
    public CreateCommentResponse save(SessionUser sessionUser, Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        User user = userService.getUserById(sessionUser.getId());
        Comment comment = new Comment(schedule, user, request.getContent());
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponse.from(savedComment);
    }
    // TODO : 댓글 단건 조회

    public GetCommentResponse getOneComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchCommentException("존재하지 않는 댓글입니다.")
        );
        return GetCommentResponse.from(comment);

    }
    // 댓글 전체 조회


    public List<GetCommentResponse> getCommentsByScheduleId(Long scheduleId) {
        List<Comment> commentList = commentRepository.findCommentsByScheduleId(scheduleId);
        return commentList.stream().map(GetCommentResponse::from).toList();
    }
    // 댓글 업데이트

    @Transactional
    public UpdateCommentResponse update(SessionUser sessionUser, Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchCommentException("존재하지 않는 댓글입니다.")
        );
        // 로그인 한 유저가 댓글을 단 유저가 맞는지 확인
        if(!Objects.equals(sessionUser.getId(), comment.getUser().getId())){
            throw new UnAuthorizedUserException("댓글 수정 권한이 없는 유저입니다.");
        }
        comment.setContent(request.getContent());
        comment.setModifiedDate(LocalDateTime.now());
        return UpdateCommentResponse.from(comment);
    }
    @Transactional
    public void deleteComment(Long commentId) {
        boolean existence = commentRepository.existsById(commentId);
        if(!existence){
            throw new NoSuchCommentException("존재하지 않는 댓글입니다.");
        }
        commentRepository.deleteById(commentId);
    }


}
