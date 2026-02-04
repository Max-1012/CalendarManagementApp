package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.CreateScheduleRequest;
import org.calendarmanagement.dto.CreateScheduleResponse;
import org.calendarmanagement.dto.GetScheduleResponse;
import org.calendarmanagement.entity.Schedule;
import org.calendarmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        if(request.getAuthor()==null || request.getTitle()==null || request.getPassword()==null){
            throw new IllegalStateException("일정 제목, 작성자명, 비밀번호는 필수입니다.");
        }

        // 스케쥴 생성
        Schedule newSchedule = new Schedule(request.getTitle(), request.getContent(),request.getAuthor(), request.getPassword());
        // 스케쥴 저장
        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return new CreateScheduleResponse(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent()
                , savedSchedule.getAuthor(), savedSchedule.getCreatedDate(),savedSchedule.getModifiedDate());

    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        return new GetScheduleResponse(schedule.getId(),schedule.getTitle(),schedule.getContent()
                ,schedule.getAuthor(),schedule.getCreatedDate(),schedule.getModifiedDate());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getSchedules(String author) {
        List<Schedule> schedules;
        List<GetScheduleResponse> responseSchedules;
        // 작성자를 지정하지 않으면
        if(author.isEmpty()){
            schedules = scheduleRepository.findAll();
        }else{
            // 작성자를 지정하면
            schedules = scheduleRepository.findAll().stream().filter(
                    schedule -> schedule.getTitle().equals(author)
            ).toList();
        }
        return schedules.stream().map(schedule -> new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent()
                , schedule.getAuthor(), schedule.getCreatedDate(), schedule.getModifiedDate())).toList();

    }
}
