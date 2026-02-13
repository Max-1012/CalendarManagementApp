package org.calendarmanagement.repository;

import org.calendarmanagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("select c from Comment c where c.schedule.id = :scheduleId")
    List<Comment> findCommentsByScheduleId(Long scheduleId);

}
