package org.calendarmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.exception.NoSuchInstanceException;
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
        CreateCommentResponse response;
        try{
            response = commentService.save(scheduleId,request);
        }catch (Exception e){
            if(e instanceof NoSuchInstanceException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
