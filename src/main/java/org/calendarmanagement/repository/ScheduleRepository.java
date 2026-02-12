package org.calendarmanagement.repository;

import org.calendarmanagement.entity.Schedule;
import org.calendarmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    @Query("SELECT s from Schedule s where s.user.userName = :userName")
    List<Schedule> findAllByUserName(@Param("userName") String userName);
}
