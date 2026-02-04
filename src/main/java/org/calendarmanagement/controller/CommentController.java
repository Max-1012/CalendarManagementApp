package org.calendarmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.request.CreateCommentRequest;
import org.calendarmanagement.dto.response.CreateCommentResponse;
import org.calendarmanagement.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public ResponseEntity<CreateCommentResponse> saveComment(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request){
        CreateCommentResponse response = commentService.save(scheduleId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
