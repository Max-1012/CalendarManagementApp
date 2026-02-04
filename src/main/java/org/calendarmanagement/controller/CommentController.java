package org.calendarmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

}
