package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.CreateScheduleRequest;
import org.calendarmanagement.dto.CreateScheduleResponse;
import org.calendarmanagement.dto.GetScheduleResponse;
import org.calendarmanagement.entity.Author;
import org.calendarmanagement.entity.Schedule;
import org.calendarmanagement.repository.AuthorRepository;
import org.calendarmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AuthorRepository authorRepository;


    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        if(request.getAuthorName()==null || request.getTitle()==null || request.getPassword()==null){
            throw new IllegalStateException("일정 제목, 작성자명, 비밀번호는 필수입니다.");
        }
        // 이미 존재하는 작성자인지 확인
        boolean existence = authorRepository.existsByName(request.getAuthorName());
        Author author;
        // 존재하지 않는 작성자이면 생성
        if(!existence){
            author = authorRepository.save(new Author(request.getAuthorName(), request.getPassword()));

        }else{
            // 이미 존재하는 작성자이면 찾아오기
            author = authorRepository.findByName(request.getAuthorName());
        }
        // 스케쥴 생성
        Schedule newSchedule = new Schedule(request.getTitle(), request.getContent(), author);
        // 스케쥴 저장
        Schedule savedSchedule = scheduleRepository.save(newSchedule);
        author.addSchedule(savedSchedule);

        return new CreateScheduleResponse(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent()
                ,author.getName(),savedSchedule.getCreatedDate(),savedSchedule.getModifiedDate());

    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        return new GetScheduleResponse(schedule.getId(),schedule.getTitle(),schedule.getContent()
                ,schedule.getAuthor().getName(),schedule.getCreatedDate(),schedule.getModifiedDate());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getSchedules(String name) {
        List<Schedule> schedules;
        List<GetScheduleResponse> responseSchedules;
        if(name.isEmpty()){
            schedules = scheduleRepository.findAll();
        }else{
            Author author = authorRepository.findByName(name);
            schedules = author.getSchedules();
        }
        return schedules.stream().map(schedule -> new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent()
                , schedule.getAuthor().getName(), schedule.getCreatedDate(), schedule.getModifiedDate())).toList();

    }
}
