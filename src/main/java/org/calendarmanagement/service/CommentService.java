package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;



}
