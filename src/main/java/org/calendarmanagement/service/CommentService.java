package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.exception.CommentException;
import org.calendarmanagement.exception.ErrorCode;
import org.calendarmanagement.exception.UserException;
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
        if(sessionUser==null){
            throw new UserException(ErrorCode.UNAUTHENTICATED_USER);
        }
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        User user = userService.getUserById(sessionUser.getId());
        Comment comment = new Comment(schedule, user, request.content());
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponse.of(savedComment);
    }

    // 댓글 단건 조회
    public GetCommentResponse getOneComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentException(ErrorCode.NO_SUCH_COMMENT)
        );
        return GetCommentResponse.of(comment);
    }

    // 댓글 전체 조회는, scheduleService에서 스케쥴 단건 조회 시 commentRepository 사용해서 구현

    // 댓글 업데이트
    @Transactional
    public UpdateCommentResponse update(SessionUser sessionUser, Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CommentException(ErrorCode.NO_SUCH_COMMENT)
        );
        // 로그인 한 유저가 댓글을 단 유저가 맞는지 확인
        if(!Objects.equals(sessionUser.getId(), comment.getUser().getId())){
            throw new UserException(ErrorCode.UNAUTHORIZED_USER);
        }
        comment.setContent(request.content());
        comment.setModifiedDate(LocalDateTime.now());
        return UpdateCommentResponse.of(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        boolean existence = commentRepository.existsById(commentId);
        if(!existence){
            throw new CommentException(ErrorCode.NO_SUCH_COMMENT);
        }
        commentRepository.deleteById(commentId);
    }


}
