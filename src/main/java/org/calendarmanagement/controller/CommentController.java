package org.calendarmanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.commentDto.request.CreateCommentRequest;
import org.calendarmanagement.dto.commentDto.request.UpdateCommentRequest;
import org.calendarmanagement.dto.commentDto.response.CreateCommentResponse;
import org.calendarmanagement.dto.commentDto.response.GetCommentResponse;
import org.calendarmanagement.dto.commentDto.response.UpdateCommentResponse;
import org.calendarmanagement.dto.userDto.SessionUser;
import org.calendarmanagement.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping()
    public ResponseEntity<CreateCommentResponse> saveComment(
            @SessionAttribute(name = "loginUser",required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequest request){
        CreateCommentResponse response = commentService.save(sessionUser,scheduleId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // TODO : 댓글 단건 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<GetCommentResponse> getComment(@PathVariable Long commentId){
        GetCommentResponse response = commentService.getOneComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 댓글 업데이트
    @PutMapping("/{commentId}")
    public ResponseEntity<UpdateCommentResponse> updateComment(
            @SessionAttribute(name = "loginUser",required = false) SessionUser sessionUser,
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentRequest request
    ){
        UpdateCommentResponse response = commentService.update(sessionUser,commentId,request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // TODO : 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
