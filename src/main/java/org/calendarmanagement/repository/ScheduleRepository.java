package org.calendarmanagement.repository;

import org.calendarmanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Schedule findByAuthor(String author);
}
